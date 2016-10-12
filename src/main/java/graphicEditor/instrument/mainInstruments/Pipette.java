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
     * Этот объект в FXML
     */
    private Button pipetteButton;
    private Image pipetteIcon = new Image("/images/buttons/pipetteIcon.png");
    private Cursor pipetteCursor = new ImageCursor(new Image("/images/cursors/pipetteCursor.png"), 2, 1);


    //Конструктор
    public Pipette(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.deskCanvas;
        pipetteButton = controller.pipetteButton;
        setIcon(pipetteButton, pipetteIcon);
        run();
    }

    //Выполнение
    public void run(){
        pipetteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument();
                setCursor(deskCanvas, pipetteCursor);
                setInstrumentIcon(pipetteIcon);
            }
        });
    }

    //Сделать этот инструмент активным
    public void setActiveInstrument(){
        activeInstrument = this;
    }

    //Применение пипетки
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
