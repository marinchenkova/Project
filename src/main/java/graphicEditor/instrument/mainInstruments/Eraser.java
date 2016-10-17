package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import sun.util.calendar.Era;

/**
 * Ластик
 */
public class Eraser extends Instrument {

    private  Controller controller;
    private Eraser eraser;

    /**
     * Этот объект в FXML
     */
    private Button eraserButton;
    private Image eraserIcon = new Image("/images/buttons/eraserIcon.png");
    private Cursor eraserCursor = new ImageCursor(new Image("/images/cursors/eraserCursor.png"), 2, 2);

    /**
     * Конструктор
     */
    public Eraser(Controller controller) {
        this.controller = controller;
        eraser = this;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        eraserButton = controller.getEraserButton();
        setIcon(eraserButton, eraserIcon);

        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        //Нажатие мыши
        eraserButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(eraser);
                setDeskCursor(deskCanvas, eraserCursor);
                setActiveInstrumentIcon(eraserIcon);
            }
        });
    }

    /**
     * Применение ластика
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(backgroundColor);
        graphicsContext.fillRect(event.getX(), event.getY(), 16, 16);
    }

}
