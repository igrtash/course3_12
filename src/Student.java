public class Student {
    public String name;
    public int age;
    public String nomGroup;

    public Student() {
    }

    public Student(String name, int age, String nomGroup) {
        this.name = name;
        this.age = age;
        this.nomGroup = nomGroup;
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

    public String getnomGroup() {
        return nomGroup;
    }

    public void setnomGroup(String nomGroup) {
        this.nomGroup = nomGroup;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nomGroup='" + nomGroup + '\'' +
                '}';
    }
}
