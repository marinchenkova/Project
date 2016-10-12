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
    private Button rectangleButton;
    private Image rectangleIcon = new Image("/images/buttons/rectangleIcon.png");


    //Конструктор
    public Rectangle(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        rectangleButton = controller.rectangleButton;
        setIcon(rectangleButton, rectangleIcon);
        run();
    }

    //Выполнение
    public void run(){
        //Нажатие мыши
        rectangleButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument();
                setCursor(deskCanvas, figureCursor);
                setInstrumentIcon(rectangleIcon);
            }
        });
    }

    //Сделать этот инструмент активным
    public void setActiveInstrument(){
        activeInstrument = this;
    }

    //Применение эллипса
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
