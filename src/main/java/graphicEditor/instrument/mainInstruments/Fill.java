package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Заливка
 */
public class Fill extends Instrument {
    /**
     * Поля
     */
    //Класс - контроллер
    private Controller controller;

    //Поля для кнопки
    private Button fillButton;
    private Image buttonIcon = new Image("/images/buttons/fillButton.png");


    //Конструктор
    public Fill(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        fillButton = controller.fillButton;
        setIcon(fillButton, buttonIcon);
    }
}
