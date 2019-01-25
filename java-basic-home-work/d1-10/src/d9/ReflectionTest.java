package d9;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReflectionTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

//        String[] names = new String[10];
//        for (int i = 0; i < names.length; i++) {
//            names[i] = "John";
//        }
//        clazz = String.class;
//        clazz = Class.forName("java.lang.String");
        String name = "John";
       // test(name);
        //System.out.println(name);
        //
        System.out.println("===========testInvokeAllMethodsWoParams");
        testInvokeAllMethodsWoParams(name);
        System.out.println("===========testGetFinalMethods");
        testGetFinalMethods(new HashMap<>());
        System.out.println("===========getParents");
        System.out.println(getParents(new HashMap<>().getClass()));
        System.out.println("===========toNullValues");
        toNullValues(name);
        System.out.println("===========after");
        getFieldsAndValues(name);

//        System.out.println(name);
//        for (String s : names) {
//            System.out.println(s);
//        }

    }

    //jvm option
//    --illegal-access=permit
    //change value of private field
    static void test(Object name) {
        Class<?> clazz = name.getClass(); // String
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("value")) {
                System.out.println(field);
                String newValue = "John sucks";
                try {
                    field.setAccessible(true);
                    field.set(name, newValue.getBytes());
                    //j8
                    //field.set(name, newValue.toCharArray());
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //    Метод принимает object и вызывает у него все методы без параметров
    static void testInvokeAllMethodsWoParams(Object name) {
        Class<?> clazz = name.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterCount() == 0) {
                try {
                    method.setAccessible(true);
                    System.out.println("===>" + method.getName() + "<===");
                    System.out.println(method.invoke(name));
                    method.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //    Метод принимает object и выводит на экран все сигнатуры методов в которых есть final
    static void testGetFinalMethods(Object name) {
        Class<?> clazz = name.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isFinal(method.getModifiers())) {
                System.out.println(method.getName());
            }
        }

    }

    //    Метод принимает object и выводит на экран все поля и значения
    static void getFieldsAndValues(Object name) throws IllegalAccessException {
        Class<?> clazz = name.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.toString() + "==" + field.get(name));
            field.setAccessible(false);
        }

    }


    //    Метод принимает Class и возвращает список всех предков класса и все интерфейсы которое класс имплементирует
    static List<Class> getParents(Class clazz) {
        List<Class> list = new ArrayList<>();
        list.addAll(Arrays.asList(clazz.getInterfaces()));
        list.addAll(Arrays.asList(clazz.getClasses()));
        return list;
    }

    //    Метод принимает объект и меняет всего его поля на их нулевые значение (null, 0, false etc)+
    static void toNullValues(Object name) throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = name.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Field mod = Field.class.getDeclaredField("modifiers");
            mod.setAccessible(true);
            mod.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            Class fieldType = field.getType();

            if (fieldType.isPrimitive()) {
                if (fieldType.isAssignableFrom(boolean.class)) {
                    field.set(name, false);
                } else if (fieldType.isAssignableFrom(char.class)) {
                    field.set(name, '\u0000');
                } else if (fieldType.isAssignableFrom(long.class)) {
                    field.set(name, 0L);
                } else if (fieldType.isAssignableFrom(float.class)) {
                    field.set(name, 0.0F);
                } else if (fieldType.isAssignableFrom(double.class)) {
                    field.set(name, 0.0);
                } else if (fieldType.isAssignableFrom(byte.class)) {
                    field.set(name, 0);
                } else if (fieldType.isAssignableFrom(short.class)) {
                    field.set(name, 0);
                } else if (fieldType.isAssignableFrom(int.class)) {
                    field.set(name, 0);
                }

            } else {
                field.set(name, null);
            }

            mod.setAccessible(false);
            field.setAccessible(false);

        }

    }
}