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
    private Button brushButton;
    private Image buttonIcon = new Image("/images/buttons/brushButton.png");
    private Cursor cursorImage = new ImageCursor(new Image("/images/cursors/brushCursor.png"), 15, 15);

    //Конструктор
    public Brush(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.deskCanvas;

        //Инструмент кисть выбирается по умолчанию
        setActiveInstrument();

        brushButton = controller.brushButton;
        setIcon(brushButton, buttonIcon);
        setCursor(deskCanvas, cursorImage);

        run();
    }

    //Выполнение
    public void run(){
        //Нажатие мыши
        brushButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(deskCanvas, cursorImage);
                setActiveInstrument();
            }
        });
    }

    //Сделать этот инструмент активным
    public void setActiveInstrument(){
        activeInstrument = this;
    }

    //Применение кисти
    @Override
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(activeColor);

        graphicsContext.fillOval(event.getX(), event.getY(), 4, 4);

    }

}
