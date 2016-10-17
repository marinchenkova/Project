package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.*;
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
     * Применение кисти
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(activeColor);
        graphicsContext.fillOval(event.getX() - lineWidth/2 + 1, event.getY() - lineWidth/2 + 1, lineWidth, lineWidth);

    }

}
