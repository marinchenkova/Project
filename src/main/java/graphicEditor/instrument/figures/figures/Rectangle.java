package graphicEditor.instrument.figures.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figures.Figure;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Прямоугольник
 */
public class Rectangle extends Figure {

    private  Controller controller;
    private Rectangle rectangle;

    /**
     * Этот объект в FXML
     */
    private Button rectangleButton;
    private Image rectangleIcon = new Image("/images/buttons/rectangleIcon.png");


    /**
     * Конструктор
     */
    public Rectangle(Controller controller) {
        this.controller = controller;
        rectangle = this;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        rectangleButton = controller.getRectangleButton();
        setIcon(rectangleButton, rectangleIcon);
        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        //Нажатие мыши
        rectangleButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(rectangle);
                setDeskCursor(deskCanvas, figureCursor);
                setActiveInstrumentIcon(rectangleIcon);
            }
        });
    }

    /**
     * Применение прямоугольника
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
