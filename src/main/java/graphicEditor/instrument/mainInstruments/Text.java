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

    private  Controller controller;
    private Text text;

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
        text = this;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        textButton = controller.getTextButton();
        setIcon(textButton, textIcon);
        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        textButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(text);
                setDeskCursor(deskCanvas, textCursor);
                setActiveInstrumentIcon(textIcon);
            }
        });
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
