package graphicEditor.instrument.figures.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.figures.Figure;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Эллипс
 */
public class Ellipse extends Figure {

    private  Controller controller;
    private Ellipse ellipse;
    /**
     * Этот объект в FXML
     */
    private Button ellipseButton;
    private Image ellipseIcon = new Image("/images/buttons/ellipseIcon.png");


    /**
     * Конструктор
     */
    public Ellipse(Controller controller) {
        this.controller = controller;
        ellipse = this;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        ellipseButton = controller.getEllipseButton();
        setIcon(ellipseButton, ellipseIcon);
        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        //Нажатие мыши
        ellipseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setActiveInstrument(ellipse);
                setDeskCursor(deskCanvas, figureCursor);
                setActiveInstrumentIcon(ellipseIcon);
            }
        });
    }

    /**
     * Применение эллипса
     * @param event
     * @param graphicsContext
     */
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){

    }
}
