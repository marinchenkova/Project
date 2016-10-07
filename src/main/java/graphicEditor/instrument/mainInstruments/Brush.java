package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.*;
import graphicEditor.instrument.desk.Desk;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


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
    private Cursor cursorImage = new ImageCursor(new Image("/images/cursors/brushCursor.png"));

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
        //desk.activeInstrument = brush;

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

    //Инструмент выбран
    public void setActiveInstrument(){
        activeInstrument = this;
    }

    //
    public void mousePressedAction(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(event.getX(), event.getY(), 4, 4);
    }

}
