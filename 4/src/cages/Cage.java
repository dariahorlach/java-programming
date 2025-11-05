package cages;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cage<T extends Animal> {
    private final int capacity;
    private final List<T> animals = new ArrayList<>();

    public Cage(int capacity) {
        this.capacity = capacity;
    }

    public void addAnimal(T animal) throws Exception {
        if (animals.size() >= capacity) {
            throw new Exception("Вольєр переповнений!");
        }
        animals.add(animal);
    }

    public void removeAnimal(T animal) throws Exception {
        if (!animals.remove(animal)) {
            throw new Exception("Такої тварини немає у вольєрі!");
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupied() {
        return animals.size();
    }

    public List<T> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + animals.size() + "/" + capacity + "]: " + animals;
    }
}
