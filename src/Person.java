public class Person {
    public String name;
    public int age;
    public int yearBirthday;

    public Person() {
    }

    public Person(String name, int age, int yearBirthday) {
        this.name = name;
        this.age = age;
        this.yearBirthday = yearBirthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYearBirthday() {
        return yearBirthday;
    }

    public void setYearBirthday(int yearBirthday) {
        this.yearBirthday = yearBirthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", yearBirthday=" + yearBirthday +
                '}';
    }
}
