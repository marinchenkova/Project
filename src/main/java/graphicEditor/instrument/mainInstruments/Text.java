package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Ввод текста
 */
public class Text extends Instrument {
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
    private Button textButton;
    private Image textIcon = new Image("/images/buttons/textIcon.png");
    private Cursor textCursor = Cursor.TEXT;


    /**
     * Конструктор
     */
    public Text(Controller controller) {
        this.controller = controller;
        initialize();
    }

    /**
     * Инициализация
     */
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        textButton = controller.textButton;
        setIcon(textButton, textIcon);
        run();
    }

    /**
     * Выполнение
     */
    public void run(){
        textButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument();
                setCursor(deskCanvas, textCursor);
                setInstrumentIcon(textIcon);
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
     * Применение текста
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
