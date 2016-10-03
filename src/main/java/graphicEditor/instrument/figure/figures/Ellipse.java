package graphicEditor.instrument.figure.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figure.Figure;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Эллипс
 */
public class Ellipse extends Figure {
    /**
     * Поля
     */
    //Класс - контроллер
    private Controller controller;

    //Поля для кнопки
    private Button ellipseButton;
    private Image buttonIcon = new Image("/images/buttons/ellipseButton.png");


    //Конструктор
    public Ellipse(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        ellipseButton = controller.ellipseButton;
        setIcon(ellipseButton, buttonIcon);
    }
}
