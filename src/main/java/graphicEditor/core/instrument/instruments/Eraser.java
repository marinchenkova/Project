package graphicEditor.core.instrument.instruments;

import graphicEditor.Controller;
import graphicEditor.core.instrument.Instrument;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Ластик
 */
public class Eraser {//extends Instrument {
/*
    private Controller controller;
    private Eraser eraser;


    private Button eraserButton;
    private Image eraserIcon = new Image("/images/buttons/eraserIcon.png");
    private Cursor eraserCursor = new ImageCursor(new Image("/images/cursors/eraserCursor.png"), 2, 2);


    public Eraser(Controller controller) {
        this.controller = controller;
        eraser = this;
        initialize();
    }


    private void initialize(){
        eraserButton = controller.getEraserButton();
        setIcon(eraserButton, eraserIcon);

        run();
    }


    private void run(){
        //Нажатие мыши
        eraserButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(eraser);
                setDeskCursor(deskCanvas, eraserCursor);
                setActiveInstrumentIcon(eraserIcon);
            }
        });
    }


    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(backgroundColor);
        graphicsContext.fillRect(event.getX(), event.getY(), 16, 16);
        //isOnPainted ? paintedElements.remove(elementNumber) :
    }

    public void destructPainted(){

    }
    */
}
