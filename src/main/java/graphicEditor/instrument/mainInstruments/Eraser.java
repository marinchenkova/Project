package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Ластик
 */
public class Eraser extends Instrument {
    /**
     * Поля
     */
    //Класс - контроллер
    private Controller controller;

    //Поля для кнопки
    private Button eraserButton;
    private Image buttonIcon = new Image("/images/buttons/eraserButton.png");


    //Конструктор
    public Eraser(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        eraserButton = controller.eraserButton;
        setIcon(eraserButton, buttonIcon);
    }
}
