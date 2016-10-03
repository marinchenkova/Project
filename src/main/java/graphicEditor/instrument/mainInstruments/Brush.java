package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.MainApp;
import graphicEditor.instrument.Instrument;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * Кисть
 */
public class Brush extends Instrument {
    /**
     * Поля
     */
    //Класс - контроллер
    private  Controller controller;

    //Поля для кнопки
    private  Button brushButton;
    private Image buttonIcon = new Image("/images/buttons/brushButton.png");


    //Конструктор
    public Brush(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        brushButton = controller.brushButton;
        setIcon(brushButton, buttonIcon);
    }



    //Нажатие мыши
    public static void buttonEvent(){
        /*Button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

            }
        }); */
    }

}
