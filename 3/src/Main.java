import controller.ShapeController;
import model.Circle;
import model.Rectangle;
import model.Triangle;
import view.ShapeView;

public class Main {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);

        controller.generateShapes();

        controller.showAllShapes();
        controller.totalArea();
        controller.totalAreaByType(Rectangle.class);
        controller.totalAreaByType(Circle.class);
        controller.totalAreaByType(Triangle.class);

        controller.sortByArea();
        controller.sortByColor();
    }
}
