import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        CustomClassLoader loader = new CustomClassLoader();

        Class<?> clazz = loader.loadClass("Test");
        Object obj = clazz.getDeclaredConstructor().newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj.getClass().getClassLoader());
    }
}


