import animals.*;
import cages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZooTest {
    private Zoo zoo;
    private LionCage lionCage;
    private HoofedCage hoofedCage;
    private BirdCage birdCage;

    @BeforeEach
    void setUp() throws Exception {
        zoo = new Zoo();

        lionCage = new LionCage(2);
        hoofedCage = new HoofedCage(3);
        birdCage = new BirdCage(2);

        lionCage.addAnimal(new Lion("Сімба"));
        lionCage.addAnimal(new Lion("Муфаса"));

        hoofedCage.addAnimal(new Zebra("Марти"));
        hoofedCage.addAnimal(new Giraffe("Мелман"));

        birdCage.addAnimal(new Eagle("Орел"));

        zoo.addCage(lionCage);
        zoo.addCage(hoofedCage);
        zoo.addCage(birdCage);
    }

    @Test
    void testAddAnimalToCage() throws Exception {
        Zebra zebra = new Zebra("Нова Зебра");
        hoofedCage.addAnimal(zebra);
        assertEquals(3, hoofedCage.getOccupied(), "Вольєр для копитних повинен містити 3 тварини");
    }

    @Test
    void testRemoveAnimalFromCage() throws Exception {
        Giraffe giraffe = new Giraffe("test");
        hoofedCage.removeAnimal(giraffe);
        assertEquals(1, hoofedCage.getOccupied(), "Після видалення у вольєрі має бути 1 тварина");
    }

    @Test
    void testAddAnimalBeyondCapacityThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> lionCage.addAnimal(new Lion("Нала")));
        assertEquals("Вольєр переповнений!", exception.getMessage());
    }

    @Test
    void testRemoveNonExistingAnimalThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> birdCage.removeAnimal(new Eagle("Сокіл")));
        assertEquals("Такої тварини немає у вольєрі!", exception.getMessage());
    }

    @Test
    void testZooCountOfAnimals() {
        assertEquals(5, zoo.getCountOfAnimals(), "У зоопарку має бути 5 тварин");
    }
}
