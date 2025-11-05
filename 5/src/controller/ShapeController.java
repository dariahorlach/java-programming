package controller;

import model.*;
import view.ShapeView;

import java.io.*;
import java.util.*;
public class ShapeController {
    private final List<Shape> shapes;
    private final ShapeView view;

    public ShapeController(ShapeView view) {
        this.view = view;
        this.shapes = new ArrayList<>();
    }

    public void generateShapes() {
        shapes.add(new Rectangle("Red", 4, 6));
        shapes.add(new Circle("Blue", 3));
        shapes.add(new Triangle("Green", 4, 5));
        shapes.add(new Rectangle("Yellow", 2, 8));
        shapes.add(new Circle("Red", 5));
        shapes.add(new Triangle("Blue", 6, 7));
        shapes.add(new Rectangle("Pink", 3, 3));
        shapes.add(new Circle("Orange", 2));
        shapes.add(new Triangle("Black", 8, 4));
        shapes.add(new Rectangle("Gray", 7, 1));
    }

    public void showAllShapes() {
        view.displayShapes(shapes);
    }

    public void totalArea() {
        double total = shapes.stream().mapToDouble(Shape::calcArea).sum();
        view.displayMessage("Загальна площа всіх фігур: " + total);
    }

    public void totalAreaByType(Class<?> type) {
        double total = shapes.stream()
                .filter(s -> s.getClass() == type)
                .mapToDouble(Shape::calcArea)
                .sum();
        view.displayMessage("Загальна площа фігур типу " + type.getSimpleName() + ": " + total);
    }

    public void sortByArea() {
        shapes.sort(Comparator.comparingDouble(Shape::calcArea));
        view.displayMessage("\nСортування за площею");
        view.displayShapes(shapes);
    }

    public void sortByColor() {
        shapes.sort(Comparator.comparing(Shape::getShapeColor));
        view.displayMessage("\nСортування за кольором");
        view.displayShapes(shapes);
    }
    public void saveShapesToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shapes);
            view.displayMessage("Фігури збережено у файл: " + filename);
        } catch (IOException e) {
            view.displayMessage("Помилка при збереженні: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadShapesFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<Shape> loaded = (List<Shape>) ois.readObject();
            shapes.clear();
            shapes.addAll(loaded);
            view.displayMessage("Фігури успішно завантажено з файлу: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            view.displayMessage("Помилка при читанні: " + e.getMessage());
        }
    }
}
