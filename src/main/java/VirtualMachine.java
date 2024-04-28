import classloader.JClassLoader;
import classloader.searchpath.ClassSearchPath;
import classloader.searchpath.ModuleSearchPath;
import lombok.Getter;

public class VirtualMachine {
    @Getter
    private final JClassLoader bootstrapLoader;
    @Getter
    private final JClassLoader userLoader;

    public VirtualMachine(String userClassPath){
        bootstrapLoader = new JClassLoader(null, getSystemSearchPaths());
        userLoader = new JClassLoader(bootstrapLoader, ClassSearchPath.constructSearchPaths(userClassPath));
    }

    private static ClassSearchPath[] getSystemSearchPaths() {
        // JDK version < 9
        var bootClassPath = System.getProperty("sun.boot.class.path");
        if (bootClassPath != null) return ClassSearchPath.constructSearchPaths(bootClassPath);

        // JDK 9+
        return new ClassSearchPath[] {new ModuleSearchPath()};
    }

}
