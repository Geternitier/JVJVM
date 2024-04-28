package classloader.searchpath;

import java.io.Closeable;
import java.io.InputStream;

public interface ClassSearchPath extends Closeable {
    static ClassSearchPath[] constructSearchPaths(String path){
        String separator = System.getProperty("path.separator");
        String[] separatedPaths = path.split(separator);

        ClassSearchPath[] paths = new ClassSearchPath[separatedPaths.length];
        for(int i = 0;i < separatedPaths.length;i++){
            if(separatedPaths[i].endsWith(".jar") || separatedPaths[i].endsWith(".JAR")){
                paths[i] = new JarSearchPath(separatedPaths[i]);
            } else {
                paths[i] = new DirSearchPath(separatedPaths[i]);
            }
        }
        return paths;
    }

    InputStream findClass(String name);
}
