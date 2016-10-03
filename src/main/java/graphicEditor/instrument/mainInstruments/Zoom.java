package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Лупа
 */
public class Zoom extends Instrument {
    /**
     * Поля
     */
    //Класс - контроллер
    private Controller controller;

    //Поля для кнопки
    private Button zoomButton;
    private Image buttonIcon = new Image("/images/buttons/zoomButton.png");


    //Конструктор
    public Zoom(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        zoomButton = controller.zoomButton;
        setIcon(zoomButton, buttonIcon);
    }
}
