package classloader.searchpath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class DirSearchPath implements ClassSearchPath{
    private final Path dirPath;

    public DirSearchPath(String path) {
        dirPath = FileSystems.getDefault().getPath(path);
    }

    @Override
    public InputStream findClass(String name) {
        try {
            return new FileInputStream(dirPath.resolve(name+".class").toFile());
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public void close() {
    }
}
