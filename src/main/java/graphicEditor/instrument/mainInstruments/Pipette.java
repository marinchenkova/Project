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

    private  Controller controller;
    private Pipette pipette;

    /**
     * Этот объект в FXML
     */
    private Button pipetteButton;
    private Image pipetteIcon = new Image("/images/buttons/pipetteIcon.png");
    private Cursor pipetteCursor = new ImageCursor(new Image("/images/cursors/pipetteCursor.png"), 2, 1);


    /**
     * Конструктор
     */
    public Pipette(Controller controller) {
        pipette = this;
        this.controller = controller;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        pipetteButton = controller.getPipetteButton();
        setIcon(pipetteButton, pipetteIcon);
        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        pipetteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(pipette);
                setDeskCursor(deskCanvas, pipetteCursor);
                setActiveInstrumentIcon(pipetteIcon);
            }
        });
    }

    /**
     * Применение пипетки
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
