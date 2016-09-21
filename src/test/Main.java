package test;

import java.lang.reflect.*;

// с помощью рефлексии можно получить доступ ко всем конструкторы, методы, все параметры, возвращаемые значения, модификаторы, поля...
// можно создавать новые объекты
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        test.SomeClass someClass = new test.SomeClass();

        Class clss1 = someClass.getClass();
        System.out.println("Имя класса: " + clss1.getName()); // имя класса
        Class clss2 = test.SomeClass.class;
        Class clss3 = Class.forName("test.SomeClass");

        SomeClass someClass1 = (SomeClass) clss1.newInstance();

        System.out.println("Конструкторы:");
        Constructor[] constructors = clss1.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName()); // имена конструкторов
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName()); // имена параметров
                System.out.println(parameter.getType().getName()); // имена типов параметров
            }
        }

        System.out.println("Методы:");
        Method[] methods = clss1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName()); // имена параметров
                System.out.println(parameter.getType().getName()); // имена типов параметров
            }
            System.out.println(Modifier.toString(method.getModifiers()));
        }

        System.out.println("Поля:");
        Field[] fields = clss1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Имя поля: " + field.getName());
            System.out.println("Имя типа поля: " + field.getType().getName());
            System.out.println("Модификаторы: " + Modifier.toString(field.getModifiers()));
        }
    }
}

class SomeClass {
    private static transient String s;
    private volatile int hahaha;

    public SomeClass() {
    }

    public SomeClass(String s) {
        this.s = s;
    }

    public synchronized String someMethod(String s) {
        System.out.println("This is " + s);
        return s;
    }
}