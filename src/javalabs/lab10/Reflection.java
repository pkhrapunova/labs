package javalabs.lab10;

import javalabs.lab7.Model.Master;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reflection {
    static Scanner scanner = new Scanner(System.in);

    public static void task1() throws ClassNotFoundException {
        System.out.println("-----------------------task1-------------------------------------------");
        System.out.print("Введите полное имя класса (пример java.lang.String): ");
        String name = scanner.nextLine();
        Class<?> clazz = Class.forName(name);
        System.out.println("Методы класса " + clazz.getName() + ":");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("Поля класса " + clazz.getName() + ":");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }


    public static void task2() throws ClassNotFoundException {
        System.out.println("\n\n-----------------------task2-------------------------------------------");
        System.out.print("Введите полное имя класса (например java.util.ArrayList): ");
        String name = scanner.nextLine();
        Class<?> clazz = Class.forName(name);
        System.out.println("Доступные методы класса " + clazz.getName() + ":");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("Доступные поля класса " + clazz.getName() + ":");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }


    @SuppressWarnings("CallToPrintStackTrace")
    public static void task3() {
        System.out.println("\n\n-----------------------task3-------------------------------------------");
        Object[] objects = { 23, "String", new ArrayList<>() };

        try (FileWriter writer = new FileWriter("class_info.txt")) {
            for (Object obj : objects) {
                writer.write(obj.getClass().getName() + "\n");
            }
            System.out.println("файл class_info создан и информация записана");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void task4() throws FileNotFoundException {
        System.out.println("\n\n-----------------------task4-------------------------------------------");
        File file = new File("class_names.txt");

        if (!file.exists()) {
            System.out.println("Файл class_names.txt не найден.");
            return;
        }
        Scanner scanner = new Scanner(file);
        List<Object> objectList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String className = scanner.nextLine().trim();
            if (className.isEmpty()) continue;

            try {
                Class<?> clazz = Class.forName(className);
                Object obj = clazz.getDeclaredConstructor().newInstance();
                objectList.add(obj);
            } catch (Exception e) {
                System.out.println("Ошибка при создании объекта " + className + ": " + e.getMessage());
            }
        }

        if (objectList.isEmpty()) {
            System.out.println("Массив пустой");
        } else {
            Object[] objects = objectList.toArray();

            System.out.println("Созданные объекты:");
            for (Object obj : objects) {
                System.out.println("Объект класса: " + obj.getClass().getName());
            }
        }
    }

    public static Object[] invokeMethodOnObjects(Object[] objects, String methodName, Object... params) throws Exception {
        Object[] results = new Object[objects.length];

        Class<?>[] paramTypes = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = getPrimitiveType(params[i].getClass());
        }

        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            Method method = obj.getClass().getMethod(methodName, paramTypes);
            results[i] = method.invoke(obj, params);
        }

        return results;
    }

    private static Class<?> getPrimitiveType(Class<?> clazz) {
        if (clazz == Integer.class) return int.class;
        if (clazz == Double.class) return double.class;
        if (clazz == Boolean.class) return boolean.class;
        if (clazz == Long.class) return long.class;
        if (clazz == Float.class) return float.class;
        if (clazz == Short.class) return short.class;
        if (clazz == Byte.class) return byte.class;
        if (clazz == Character.class) return char.class;
        return clazz;
    }


    public static void task5() throws Exception {
        System.out.println("\n\n-----------------------task5-------------------------------------------");

        Object[] objects = { "Hello", "World", "Java" };
        Object[] results = invokeMethodOnObjects(objects,"substring", 1);

        for (Object result : results) {
            System.out.println(result);
        }
    }


    public static Method[] getMethodsByReturnType(Class<?> class1, Class<?> returnType) {
        Method[] methods = class1.getDeclaredMethods();

        Method[] matchingMethods = new Method[methods.length];
        int count = 0;

        for (Method method : methods) {
            if (method.getReturnType().equals(returnType)) {
                matchingMethods[count++] = method;
            }
        }

        Method[] result = new Method[count];
        System.arraycopy(matchingMethods, 0, result, 0, count);

        return result;
    }

    public static void task6() {
        System.out.println("\n\n-----------------------task6-------------------------------------------");
        Method[] stringMethods = getMethodsByReturnType(String.class, String.class);

        System.out.println("Методы класса, которые возвращают заданый класс:");
        for (Method method : stringMethods) {
            System.out.println(method.getName());
        }
    }
    private static final String INDENT = "    ";
    public static String generateSkeleton(Class<?> clazz) {
        StringBuilder skeleton = new StringBuilder();
        skeleton.append("public class ").append(clazz.getSimpleName()).append(" {\n");

        for (Field field : clazz.getDeclaredFields()) {
            String mods = Modifier.toString(field.getModifiers());
            if (!mods.isEmpty()) skeleton.append(INDENT).append(mods).append(" ");
            else skeleton.append(INDENT);
            skeleton.append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";\n");
        }

        for (Method method : clazz.getDeclaredMethods()) {
            String mods = Modifier.toString(method.getModifiers());
            if (!mods.isEmpty()) skeleton.append(INDENT).append(mods).append(" ");
            else skeleton.append(INDENT);

            Class<?> returnType = method.getReturnType();
            skeleton.append(returnType.getSimpleName()).append(" ").append(method.getName()).append("(");

            Class<?>[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                skeleton.append(paramTypes[i].getSimpleName()).append(" arg").append(i);
                if (i < paramTypes.length - 1) skeleton.append(", ");
            }

            skeleton.append(")");

            Class<?>[] exceptions = method.getExceptionTypes();
            if (exceptions.length > 0) {
                skeleton.append(" throws ");
                for (int i = 0; i < exceptions.length; i++) {
                    skeleton.append(exceptions[i].getSimpleName());
                    if (i < exceptions.length - 1) skeleton.append(", ");
                }
            }

            skeleton.append(" {\n");
            if (!returnType.equals(void.class)) {
                skeleton.append(INDENT).append(INDENT).append("return ").append(getDefaultValue(returnType)).append(";\n");
            }
            skeleton.append(INDENT).append("}\n");
        }

        skeleton.append("}\n");
        return skeleton.toString();
    }

    private static String getDefaultValue(Class<?> type) {
        if (type.equals(int.class) || type.equals(short.class) || type.equals(byte.class)) return "0";
        if (type.equals(long.class)) return "0L";
        if (type.equals(float.class)) return "0.0f";
        if (type.equals(double.class)) return "0.0";
        if (type.equals(boolean.class)) return "false";
        if (type.equals(char.class)) return "'\\u0000'";
        return "null";
    }

    public static void task7() {
        System.out.println("\n\n-----------------------task7-------------------------------------------");
        Class<?> clazz = Master.class;
        String skeleton = generateSkeleton(clazz);
        System.out.println("Скелет класса " + clazz.getSimpleName() + ":");
        System.out.println(skeleton);
    }
}
