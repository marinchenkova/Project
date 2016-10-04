package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
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
     * Доска
     */
    private Canvas deskCanvas;

    /**
     * Этот объект в FXML
     */
    private Button eraserButton;
    private Image buttonIcon = new Image("/images/buttons/eraserButton.png");
    private Cursor cursorImage = new ImageCursor(new Image("/images/cursors/eraserCursor.png"));

    //Конструктор
    public Eraser(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.desk;
        eraserButton = controller.eraserButton;
        setIcon(eraserButton, buttonIcon);
        run();
    }

    //Выполнение
    public void run(){
        eraserButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(deskCanvas, cursorImage);
            }
        });
    }
}
