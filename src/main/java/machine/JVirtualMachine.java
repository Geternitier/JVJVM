package machine;

import classloader.JClassLoader;
import classloader.searchpath.ClassSearchPath;
import classloader.searchpath.ModuleSearchPath;
import interpreter.JInterpreter;
import lombok.Getter;
import runtime.JClass;
import runtime.JThread;
import runtime.LocalVariables;
import runtime.classdata.Method;

import java.util.ArrayList;

public class JVirtualMachine {
    @Getter
    private final JClassLoader bootstrapLoader;
    @Getter
    private final JClassLoader userLoader;
    @Getter
    private final JInterpreter interpreter;
    private final ArrayList<JThread> threads = new ArrayList<>();

    public JVirtualMachine(String userClassPath){
        bootstrapLoader = new JClassLoader(null, getSystemSearchPaths());
        userLoader = new JClassLoader(bootstrapLoader, ClassSearchPath.constructSearchPaths(userClassPath));
        interpreter = new JInterpreter();
    }

    public void run(String className){
        JThread initThread = new JThread(this);
        threads.add(initThread);

        JClass jClass = userLoader.loadClass('L' + className.replace('.', '/') + ';');
        Method mainMethod = jClass.getMethod("main", "([Ljava/lang/String;)V");

        assert mainMethod.getJClass() == jClass;
        interpreter.invoke(mainMethod, initThread, new LocalVariables(1));
    }

    private static ClassSearchPath[] getSystemSearchPaths() {
        // JDK version < 9
        var bootClassPath = System.getProperty("sun.boot.class.path");
        if (bootClassPath != null) return ClassSearchPath.constructSearchPaths(bootClassPath);

        // JDK 9+
        return new ClassSearchPath[] {new ModuleSearchPath()};
    }

    public static void main(String[] args){
        JVirtualMachine machine = new JVirtualMachine("output/");
        machine.run("Bye");
        System.out.println("HelloWorld!");
    }
}
