package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.*;
import graphicEditor.instrument.paintedElements.elements.BrushElement;
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
     * Применение кисти: при нажатии ЛКМ выполняется метод {@link BrushElement#paint}, ПКМ - выделение объекта
     * {@link Brush#elementAction}.
     * @param event событие мыши
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
        if(event.isPrimaryButtonDown()){
            if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
                paintedElements.add(new BrushElement(event, graphicsContext));
            }
            if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
                paintedElements.get(paintedElements.size()-1).paint(event, graphicsContext);
            }
            if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
                isOnPainted = true;
            }
        }

        if(event.isSecondaryButtonDown() && isOnPainted) {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                elementAction(graphicsContext);
            }
        }
    }

    /**
     * Действие с объектом {@link BrushElement}
     * @param graphicsContext
     */
    public void elementAction(GraphicsContext graphicsContext){
        paintedElements.get(elementNumber).delete(graphicsContext);
    }

}
