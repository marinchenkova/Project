package graphicEditor.instrument.figures.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figures.Figure;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Линия
 */
public class Line extends Figure {
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
    private Button lineButton;
    private Image lineIcon = new Image("/images/buttons/lineIcon.png");


    //Конструктор
    public Line(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        lineButton = controller.lineButton;
        setIcon(lineButton, lineIcon);
        run();
    }

    //Выполнение
    public void run(){
        //Нажатие мыши
        lineButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument();
                setCursor(deskCanvas, figureCursor);
                setInstrumentIcon(lineIcon);
            }
        });
    }

    //Сделать этот инструмент активным
    public void setActiveInstrument(){
        activeInstrument = this;
    }

    //Применение линии
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
