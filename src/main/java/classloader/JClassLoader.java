package classloader;

import classloader.searchpath.ClassSearchPath;
import lombok.SneakyThrows;
import runtime.JClass;

import java.io.Closeable;
import java.io.DataInputStream;
import java.util.HashMap;

public class JClassLoader implements Closeable {
    private final JClassLoader parent;
    private final ClassSearchPath[] searchPaths;
    private final HashMap<String, JClass> definedClass = new HashMap<>();

    public JClassLoader(JClassLoader parent, ClassSearchPath[] searchPaths){
        this.parent = parent;
        this.searchPaths = searchPaths;
    }

    public JClass loadClass(String descriptor){
        JClass jClass;
        if(parent != null && (jClass = parent.loadClass(descriptor)) != null) return jClass;
        if((jClass = definedClass.get(descriptor)) != null) return jClass;
        String name = descriptor.substring(1, descriptor.length()-1);
        for(ClassSearchPath path: searchPaths){
            var stream = path.findClass(name);
            if(stream != null){
                jClass = new JClass(new DataInputStream(stream), this);
                definedClass.put(descriptor, jClass);
                return jClass;
            }
        }
        return null;
    }

    @Override
    @SneakyThrows
    public void close() {
        for(ClassSearchPath path: searchPaths) path.close();
    }
}
