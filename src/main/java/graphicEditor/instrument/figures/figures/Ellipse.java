package graphicEditor.instrument.figures.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figures.Figure;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Эллипс
 */
public class Ellipse extends Figure {
    /**
     * Поля
     */

    /**
     * Класс - контроллер
     */
    private  Controller controller;

    /**
     * Этот объект в FXML
     */
    private Button ellipseButton;
    private Image buttonIcon = new Image("/images/buttons/ellipseButton.png");


    //Конструктор
    public Ellipse(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        ellipseButton = controller.ellipseButton;
        setIcon(ellipseButton, buttonIcon);
        run();
    }

    //Выполнение
    public void run(){
        ellipseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(deskCanvas, cursorImage);
            }
        });
    }
}
