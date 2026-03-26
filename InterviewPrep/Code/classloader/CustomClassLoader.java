import java.io.*;

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] loadClassData(String name) {
        String path = "customclasspath/" + name + ".class";

        try (InputStream is = new FileInputStream(path);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            int data;
            while((data = is.read()) != -1) {
                buffer.write(data);
            }
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}