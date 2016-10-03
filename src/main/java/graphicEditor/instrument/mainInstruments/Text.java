package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Ввод текста
 */
public class Text extends Instrument {
    /**
     * Поля
     */
    //Класс - контроллер
    private Controller controller;

    //Поля для кнопки
    private Button textButton;
    private Image buttonIcon = new Image("/images/buttons/textButton.png");


    //Конструктор
    public Text(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        textButton = controller.textButton;
        setIcon(textButton, buttonIcon);
    }
}
