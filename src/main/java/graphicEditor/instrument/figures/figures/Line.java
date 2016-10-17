package graphicEditor.instrument.figures.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figures.Figure;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Линия
 */
public class Line extends Figure {

    private  Controller controller;
    private Line line;

    /**
     * Этот объект в FXML
     */
    private Button lineButton;
    private Image lineIcon = new Image("/images/buttons/lineIcon.png");


    /**
     * Конструктор
     */
    public Line(Controller controller) {
        this.controller = controller;
        line = this;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        lineButton = controller.getLineButton();
        setIcon(lineButton, lineIcon);
        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        //Нажатие мыши
        lineButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(line);
                setDeskCursor(deskCanvas, figureCursor);
                setActiveInstrumentIcon(lineIcon);
            }
        });
    }

    /**
     * Применение линии
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
