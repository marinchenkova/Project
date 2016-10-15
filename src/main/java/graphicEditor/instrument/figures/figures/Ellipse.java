package graphicEditor.instrument.figures.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figures.Figure;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
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
    private Image ellipseIcon = new Image("/images/buttons/ellipseIcon.png");


    /**
     * Конструктор
     */
    public Ellipse(Controller controller) {
        this.controller = controller;
        initialize();
    }

    /**
     * Инициализация
     */
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        ellipseButton = controller.ellipseButton;
        setIcon(ellipseButton, ellipseIcon);
        run();
    }

    /**
     * Выполнение
     */
    public void run(){
        //Нажатие мыши
        ellipseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument();
                setCursor(deskCanvas, figureCursor);
                setInstrumentIcon(ellipseIcon);
            }
        });
    }

    /**
     * Сделать этот инструмент активным
     */
    public void setActiveInstrument(){
        activeInstrument = this;
    }

    /**
     * Применение эллипса
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
