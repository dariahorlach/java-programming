import animals.*;
import cages.*;

public class Main {
    public static void main(String[] args) {
        try {
            Zoo zoo = new Zoo();

            LionCage lionCage = new LionCage(2);
            HoofedCage hoofedCage = new HoofedCage(3);
            BirdCage birdCage = new BirdCage(2);

            lionCage.addAnimal(new Lion("Сімба"));
            lionCage.addAnimal(new Lion("Муфаса"));

            hoofedCage.addAnimal(new Zebra("Марти"));
            hoofedCage.addAnimal(new Giraffe("Мелман"));

            birdCage.addAnimal(new Eagle("Сокіл"));
            birdCage.addAnimal(new Eagle("Орел"));

            zoo.addCage(lionCage);
            zoo.addCage(hoofedCage);
            zoo.addCage(birdCage);

            zoo.showAllCages();
            System.out.println("Загальна кількість тварин у зоопарку: " + zoo.getCountOfAnimals());

            System.out.println("\nСпроба додати ще одного лева...");
            lionCage.addAnimal(new Lion("Нала"));
        }
        catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
