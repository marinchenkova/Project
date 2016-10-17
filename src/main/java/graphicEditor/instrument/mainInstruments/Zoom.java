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

    private  Controller controller;
    private Zoom zoom;
    /**
     * Этот объект в FXML
     */
    private Button zoomButton;
    private Image zoomIcon = new Image("/images/buttons/zoomIcon.png");
    private Cursor zoomCursor = new ImageCursor(new Image("/images/cursors/zoomCursor.png"), 8, 8);

    /**
     * Конструктор
     */
    public Zoom(Controller controller) {
        this.controller = controller;
        zoom = this;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        zoomButton = controller.getZoomButton();
        setIcon(zoomButton, zoomIcon);
        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        //Нажатие мыши
        zoomButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(zoom);
                setDeskCursor(deskCanvas, zoomCursor);
                setActiveInstrumentIcon(zoomIcon);
            }
        });
    }

    /**
     * Применение зума
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
