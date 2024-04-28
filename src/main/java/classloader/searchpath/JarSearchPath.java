package classloader.searchpath;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;

public class JarSearchPath implements ClassSearchPath{
    private final JarFile jarFile;

    public JarSearchPath(String path) {
        JarFile file;
        try {
            file = new JarFile(path);
            } catch (IOException e){
            file = null;
        }
        jarFile = file;
    }

    @Override
    @SneakyThrows
    public InputStream findClass(String name) {
        if(jarFile == null) return null;
        var jarEntry = jarFile.getEntry(name + ".class");
        if (jarEntry != null){
            return jarFile.getInputStream(jarEntry);
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        if (jarFile != null) jarFile.close();
    }
}
