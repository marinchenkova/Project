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
 * Заливка
 */
public class Fill extends Instrument {

    private  Controller controller;
    private Fill fill;

    /**
     * Этот объект в FXML
     */
    private Button fillButton;
    private Image fillIcon = new Image("/images/buttons/fillIcon.png");
    private Cursor fillCursor = new ImageCursor(new Image("/images/cursors/fillCursor.png"), 13, 31);

    /**
     * Конструктор
     */
    public Fill(Controller controller) {
        this.controller = controller;
        fill = this;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        fillButton = controller.getFillButton();
        setIcon(fillButton, fillIcon);
        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        fillButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(fill);
                setDeskCursor(deskCanvas, fillCursor);
                setActiveInstrumentIcon(fillIcon);
            }
        });
    }

    /**
     * Применение заливки
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(activeColor);

        double x = event.getX();
        double y = event.getY();
    }

}

