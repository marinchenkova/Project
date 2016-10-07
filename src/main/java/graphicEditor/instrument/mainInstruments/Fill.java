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
 * Заливка
 */
public class Fill extends Instrument {
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
    private Button fillButton;
    private Image buttonIcon = new Image("/images/buttons/fillButton.png");
    private Cursor cursorImage = new ImageCursor(new Image("/images/cursors/fillCursor.png"));

    //Конструктор
    public Fill(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        fillButton = controller.fillButton;
        setIcon(fillButton, buttonIcon);
        run();
    }

    //Выполнение
    public void run(){
        fillButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(deskCanvas, cursorImage);
            }
        });
    }
}

