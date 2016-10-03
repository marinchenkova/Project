package graphicEditor.instrument.figure.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figure.Figure;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Линия
 */
public class Line extends Figure {
    /**
     * Поля
     */
    //Класс - контроллер
    private Controller controller;

    //Поля для кнопки
    private Button lineButton;
    private Image buttonIcon = new Image("/images/buttons/lineButton.png");


    //Конструктор
    public Line(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        lineButton = controller.lineButton;
        setIcon(lineButton, buttonIcon);
    }
}
