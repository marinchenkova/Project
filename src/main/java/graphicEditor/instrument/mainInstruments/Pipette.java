package graphicEditor.instrument.mainInstruments;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Пипетка
 */
public class Pipette extends Instrument {
    /**
     * Поля
     */
    //Класс - контроллер
    private Controller controller;

    //Поля для кнопки
    private Button pipetteButton;
    private Image buttonIcon = new Image("/images/buttons/pipetteButton.png");


    //Конструктор
    public Pipette(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        pipetteButton = controller.pipetteButton;
        setIcon(pipetteButton, buttonIcon);
    }
}
