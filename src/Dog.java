import java.io.Serializable;

public class Dog implements Serializable {

    private final String name;
    private final int age;

    public Dog(final String name, final int age){
        this.name=name;
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        if (getAge() != dog.getAge()) return false;
        return getName().compareTo(dog.getName())==0;
    }
}
