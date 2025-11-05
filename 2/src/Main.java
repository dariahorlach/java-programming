import com.google.gson.Gson;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Person original = new Person("Петренко", "Іван", 25);
        System.out.println("Оригінальний об'єкт:");
        System.out.println(original);

        Gson gson = new Gson();
        String json = gson.toJson(original);
        System.out.println("\nJSON-подання:");
        System.out.println(json);

        Person restored = gson.fromJson(json, Person.class);
        System.out.println("\nВідновлений об'єкт:");
        System.out.println(restored);

        System.out.println("\nЧи рівні об'єкти? " + original.equals(restored));
    }
}

class Person {
    private String lastName;
    private String firstName;
    private int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public Person() {}

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return age == other.age &&
                Objects.equals(lastName, other.lastName) &&
                Objects.equals(firstName, other.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, age);
    }

    @Override
    public String toString() {
        return String.format("Person = {прізвище='%s', ім'я='%s', вік=%d}", lastName, firstName, age);
    }
}
