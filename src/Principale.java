public class Principale {

    public static void main(String[] args) throws Exception {

        final Dog bucky=new Dog("Bucky", 3);

        FileManager.insertOne(bucky);
        FileManager.insertOne(new Dog("Molly", 4));

        FileManager.remove(bucky);

        for (final Dog dog : FileManager.readAll())
            System.out.println(dog.getName() + " is " + dog.getAge() + " years old");

    }
}
