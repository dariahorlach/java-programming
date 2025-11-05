import cages.Cage;
import animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private final List<Cage<? extends Animal>> cages = new ArrayList<>();

    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }

    public int getCountOfAnimals() {
        int count = 0;
        for (Cage<? extends Animal> cage : cages) {
            count += cage.getOccupied();
        }
        return count;
    }

    public void showAllCages() {
        for (Cage<? extends Animal> cage : cages) {
            System.out.println(cage);
        }
    }
}
