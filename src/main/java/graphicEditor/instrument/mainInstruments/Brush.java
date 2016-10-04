package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.MainApp;
import graphicEditor.instrument.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
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
     * Доска
     */
    private Canvas deskCanvas;

    /**
     * Этот объект в FXML
     */
    private  Button brushButton;
    private Image buttonIcon = new Image("/images/buttons/brushButton.png");
    private Cursor cursorImage = new ImageCursor(new Image("/images/cursors/brushCursor.png"));

    //Конструктор
    public Brush(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        deskCanvas = controller.desk;
        brushButton = controller.brushButton;
        setIcon(brushButton, buttonIcon);
        setCursor(deskCanvas, cursorImage);
        run();
    }

    //Выполнение
    public void run(){
        brushButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
               setCursor(deskCanvas, cursorImage);
            }
        });
    }


}
