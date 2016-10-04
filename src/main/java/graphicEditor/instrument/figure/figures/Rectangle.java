package graphicEditor.instrument.figure.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figure.Figure;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Прямоугольник
 */
public class Rectangle extends Figure {
    /**
     * Поля
     */

    /**
     * Класс - контроллер
     */
    private  Controller controller;

    /**
     * Доска
     */
    private Canvas deskCanvas;

    /**
     * Этот объект в FXML
     */
    private Button rectangleButton;
    private Image buttonIcon = new Image("/images/buttons/rectangleButton.png");


    //Конструктор
    public Rectangle(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.desk;
        rectangleButton = controller.rectangleButton;
        setIcon(rectangleButton, buttonIcon);
        run();
    }

    //Выполнение
    public void run(){
        rectangleButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(deskCanvas, cursorImage);
            }
        });
    }
}
