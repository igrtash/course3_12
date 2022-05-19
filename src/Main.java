/** Урок 10 - Лямбда выражения
 * Практическое задание 12
 * Создать класс Person с полями: ФИО, возраст, год рождения.
 * Создать класс Student с полями: ФИО, возраст, группа.
 * При помощи функциональных интерфейсов сделать
 * преобразование объекта класса Person в Student.
 * При помощи функциональных интерфейсов проверить возраст
 * человека, прежде чем преобразовывать в студента. (от 17 до 40)
 * Есть две группы (УбИН-01-22 и УбИН-02-22) распределение
 * происходит по году рождения. (если год рождения < 1995, то
 * устанавливаем группу 01, в ином случае будет группа 02).
 */

import java.util.HashSet;
import java.util.Set;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        //testFuncInterfaces(); // примеры для обучения
        // задаем коллекцию персон для конвертации
        Set<Person> persons = new HashSet<>();
        persons.add(new Person("Петров Валерий Иванович", 28, 1996));
        persons.add(new Person("Сидоров Иван Анатольевич", 30, 1992));
        persons.add(new Person("Егоров Костантин Петрович", 16, 2006));
        System.out.println("persons.size = " + persons.size());
        persons.forEach(System.out::println);
        System.out.println("");

        // targetDestination. задаем коллекцию студентов начального наполнения
        HashSet<Student> students = new HashSet<>();
        students.add(new Student("Васильева Валерия Ивановна", 28, "УбИН-01-22"));
        students.add(new Student("Хохлова Наталья Анатольевна", 19, "УбИН-02-22"));
        students.add(new Student("Крутова Екатерина Петровна", 20, "УбИН-02-22"));
        System.out.println("students.size = " + students.size());
        students.forEach(System.out::println);
        System.out.println("");

        // Supplier<Person> supplierlPerson = () -> new Person(null, -1, -1);
        // к сожалению нельзя использовать параметры в Supplier

        // условие для преобразования
        Predicate<Integer> predicateAvailabilityConversion = age -> age > 17 && age < 40;

        // условие для определения номера группы
        Predicate<Integer> predicateNumGroup = yearBirthday -> yearBirthday < 1995;

        // Преобразование (конвертация) Person -> Student
        // 1 способ через функц. интерфейс Function
        Function<Person, Student> transform = (Person p) -> new Student(p.name, p.age, (predicateNumGroup.test(p.yearBirthday) ? "УбИН-01-22" : "УбИН-02-22") );
        // 2 способ, ниже (на строке 71) объявлен собственный функц. интерфейс Converter
        Converter<Person, Student> converter = (Person p) -> new Student(p.name, p.age, (predicateNumGroup.test(p.yearBirthday) ? "УбИН-01-22" : "УбИН-02-22"));

        persons.forEach(p -> {
            //System.out.println("predicate.test(age="+p.age+") = " + predicate.test(p.age));
            // проверка условия на возможность преобразования
            if (predicateAvailabilityConversion.test(p.age)) {
                //System.out.println("Do to converter for name = " + p.name + ", age = " + p.age);
                // 1-й способ
                students.add(transform.apply(p));
                // ниже 2-ой способ
                //students.add(converter.convert(p));
            }
        });
        // распечатаем коллекцию студентов после конвертации персон
        System.out.println("Студенты после конвертации персон");
        students.forEach(System.out::println);
    }

    // функциональный интерфейс для конвертации
    interface Converter<T, R> {
        R convert(T t);

        //static <T> boolean isNotNull(T t) {
        //    return t != null;
        //}

        //default void writeToConsole(T t) {
        //    System.out.println("Текущий объект - " + t.toString());
        //}

        //boolean equals(Object obj);
    }

    public static void testFuncInterfaces() {
        // List of Functional interfaces
        // https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
        // https://docs.oracle.com/javase/8/docs/api/java/util/function/package-tree.html

        Person person = new Person("Петров Валерий Иванович", 27, 1995);

        // Predicate - функциональный интерфейс для проверки соблюдения некоторого условия.
        // Если условие соблюдается, возвращает true, иначе — false
        // public interface Predicate<T> { boolean test(T t); }
        Predicate<Integer> p = age -> age > 17;
        System.out.println(p.test(person.getAge()));
        Predicate<String> p2 = name -> name.contains("е");
        System.out.println(p2.test(person.getName()));

        // Function - функциональный интерфейс принимает аргумент T и приводит его к объекту типа R,
        // который и возвращается как результат
        // public interface Function<T, R> { R apply(T t); }
        Function<Integer, String> f = String::valueOf;
        String s = f.apply(28);
        System.out.println(s);

        // UnaryOperator - функциональный интерфейс, принимает в качестве параметра объект типа T,
        // выполняет над ним некоторые операции и возвращает результат операций в виде объекта того же типа T
        // public interface UnaryOperator<T> { T apply(T t); }

        // Consumer - функциональный интерфейс, который принимает в качестве входного аргумента объект типа T,
        // совершает некоторые действия, но при этом ничего не возвращает
        // public interface Consumer<T> { void accept(T t); }
        Consumer<Person> c = pers -> {
            pers.age++;
        };
        c.accept(person);
        System.out.println(person.age);

        // Supplier - функциональный интерфейс, который не принимает никаких аргументов,
        // но возвращает некоторый объект типа T
        // public interface Supplier<T> { T get(); }
        Supplier<Person> sup = () -> new Person(null, -1, -1);
        Person person2 = sup.get();
        System.out.println(person2);

    }

}
