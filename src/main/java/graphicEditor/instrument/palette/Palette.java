package graphicEditor.instrument.palette;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.scene.paint.Color;

//TODO комментарии

/**
 * Палитра
 */
public class Palette extends Instrument{
    /**
     * Поля
     */

    /**
     * Класс - контроллер
     */
    private Controller controller;

    /**
     * Этот объект в FXML
     */

    /**
     * Конструктор
     */
    public Palette(Controller controller) {
        this.controller = controller;
        initialize();
    }

    /**
     * Инициализация
     */
    public void initialize(){
        deskCanvas = controller.deskCanvas;

        activeColor = Color.BLACK;
        backgroundColor = Color.WHITE;

        run();
    }

    /**
     * Выполнение
     */
    public void run(){

    }

}
