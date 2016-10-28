package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.*;
import graphicEditor.instrument.paintedElements.BrushElement;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Кисть
 */
public class Brush extends Instrument {

    private Controller controller;
    private Brush brush;

    /**
     * Этот объект в FXML
     */
    private Button brushButton;
    private Image brushIcon = new Image("/images/buttons/brushIcon.png");
    private Cursor brushCursor = new ImageCursor(new Image("/images/cursors/brushCursor.png"), 15, 15);

    /**
     * Конструктор
     */
    public Brush(Controller controller) {
        this.controller = controller;
        brush = this;
        initialize();
    }
    public Brush(){

    }

    /**
     * Инициализация
     */
    private void initialize(){
        //Инструмент кисть выбирается по умолчанию
        setActiveInstrument(brush);
        setActiveInstrumentIcon(brushIcon);

        brushButton = controller.getBrushButton();
        setIcon(brushButton, brushIcon);
        setDeskCursor(deskCanvas, brushCursor);

        run();
    }

    private void run(){
        //Нажатие мыши
        brushButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(brush);
                setDeskCursor(deskCanvas, brushCursor);
                setActiveInstrumentIcon(brushIcon);
            }
        });
    }

    /**
     * Применение кисти: событие мыши "клик" создает заполненный круг цвета {@link Instrument#activeColor} и
     * диаметром {@link Instrument#lineWidth}.
     * @param event событие мыши
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
        if(paintAllowed){
            if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
                brushElements.add(new BrushElement(event, graphicsContext));
            }
            if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
                brushElements.get(brushElements.size()-1).paint(event, graphicsContext);
            }
            if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
                paintAllowed = false;
            }
        } else {
            if(event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                System.err.println("DELETING");
                elementAction(graphicsContext);
            }
        }
    }

    /**
     * Действие с объектом {@link BrushElement}
     * @param graphicsContext
     */
    public void elementAction(GraphicsContext graphicsContext){
        brushElements.get(elementNumber).delete(graphicsContext);
    }

}
