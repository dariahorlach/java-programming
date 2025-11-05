package view;

import model.Shape;

import java.util.List;

public class ShapeView {
    public void displayShapes(List<Shape> shapes) {
        System.out.println("Список фігур");
        for (Shape s : shapes) {
            System.out.println(s);
        }
        System.out.println();
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}
