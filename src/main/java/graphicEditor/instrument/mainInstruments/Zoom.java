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
 * Лупа
 */
public class Zoom extends Instrument {
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
    private Button zoomButton;
    private Image zoomIcon = new Image("/images/buttons/zoomIcon.png");
    private Cursor zoomCursor = new ImageCursor(new Image("/images/cursors/zoomCursor.png"), 8, 8);

    //Конструктор
    public Zoom(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        zoomButton = controller.zoomButton;
        setIcon(zoomButton, zoomIcon);
        run();
    }

    //Выполнение
    public void run(){
        //Нажатие мыши
        zoomButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument();
                setCursor(deskCanvas, zoomCursor);
                setInstrumentIcon(zoomIcon);
            }
        });
    }

    //Сделать этот инструмент активным
    public void setActiveInstrument(){
        activeInstrument = this;
    }

    //Применение зума
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
