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
 * Пипетка
 */
public class Pipette extends Instrument {
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
    private Button pipetteButton;
    private Image buttonIcon = new Image("/images/buttons/pipetteButton.png");
    private Cursor cursorImage = new ImageCursor(new Image("/images/cursors/pipetteCursor.png"));


    //Конструктор
    public Pipette(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.desk;
        pipetteButton = controller.pipetteButton;
        setIcon(pipetteButton, buttonIcon);
        run();
    }

    //Выполнение
    public void run(){
        pipetteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(deskCanvas, cursorImage);
            }
        });
    }
}
