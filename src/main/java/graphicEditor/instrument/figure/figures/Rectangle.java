package graphicEditor.instrument.figure.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figure.Figure;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Прямоугольник
 */
public class Rectangle extends Figure {
    /**
     * Поля
     */
    //Класс - контроллер
    private Controller controller;

    //Поля для кнопки
    private Button rectangleButton;
    private Image buttonIcon = new Image("/images/buttons/rectangleButton.png");


    //Конструктор
    public Rectangle(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        rectangleButton = controller.rectangleButton;
        setIcon(rectangleButton, buttonIcon);
    }
}
