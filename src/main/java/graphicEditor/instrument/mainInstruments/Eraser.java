package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Ластик
 */
public class Eraser extends Instrument {
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
    private Button eraserButton;
    private Image eraserIcon = new Image("/images/buttons/eraserIcon.png");
    private Cursor eraserCursor = new ImageCursor(new Image("/images/cursors/eraserCursor.png"), 2, 2);

    //Конструктор
    public Eraser(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.deskCanvas;

        eraserButton = controller.eraserButton;
        setIcon(eraserButton, eraserIcon);

        run();
    }

    //Выполнение
    public void run(){
        //Нажатие мыши
        eraserButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument();
                setCursor(deskCanvas, eraserCursor);
                setInstrumentIcon(eraserIcon);
            }
        });
    }

    //Сделать этот инструмент активным
    public void setActiveInstrument(){
        activeInstrument = this;
    }

    //Применение ластика
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(backgroundColor);
        graphicsContext.fillRect(event.getX(), event.getY(), 16, 16);
    }

}
