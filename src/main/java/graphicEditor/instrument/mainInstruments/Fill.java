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
    private Image fillIcon = new Image("/images/buttons/fillIcon.png");
    private Cursor fillCursor = new ImageCursor(new Image("/images/cursors/fillCursor.png"), 13, 31);

    /**
     * Конструктор
     */
    public Fill(Controller controller) {
        this.controller = controller;
        initialize();
    }

    /**
     * Инициализация
     */
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        fillButton = controller.fillButton;
        setIcon(fillButton, fillIcon);
        run();
    }

    /**
     * Выполнение
     */
    public void run(){
        fillButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument();
                setCursor(deskCanvas, fillCursor);
                setInstrumentIcon(fillIcon);
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

