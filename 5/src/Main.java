import controller.ShapeController;
import model.*;
import view.ShapeView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        Scanner scanner = new Scanner(System.in);

        String filename = "shapes.dat";
        boolean running = true;

        while (running) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Згенерувати фігури");
            System.out.println("2. Показати всі фігури");
            System.out.println("3. Порахувати загальну площу");
            System.out.println("4. Порахувати площу за типом (Rectangle/Circle/Triangle)");
            System.out.println("5. Сортувати за площею");
            System.out.println("6. Сортувати за кольором");
            System.out.println("7. Зберегти у файл");
            System.out.println("8. Завантажити з файлу");
            System.out.println("9. Вийти");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> controller.generateShapes();
                case 2 -> controller.showAllShapes();
                case 3 -> controller.totalArea();
                case 4 -> {
                    System.out.print("Введіть тип (Rectangle/Circle/Triangle): ");
                    String type = scanner.nextLine();
                    switch (type.toLowerCase()) {
                        case "rectangle" -> controller.totalAreaByType(Rectangle.class);
                        case "circle" -> controller.totalAreaByType(Circle.class);
                        case "triangle" -> controller.totalAreaByType(Triangle.class);
                        default -> System.out.println("Невідомий тип!");
                    }
                }
                case 5 -> controller.sortByArea();
                case 6 -> controller.sortByColor();
                case 7 -> controller.saveShapesToFile(filename);
                case 8 -> controller.loadShapesFromFile(filename);
                case 9 -> running = false;
                default -> System.out.println("Невірний вибір!");
            }
        }

        System.out.println("Програма завершена.");
    }
}