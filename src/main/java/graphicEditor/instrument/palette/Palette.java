package graphicEditor.instrument.palette;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.scene.paint.Color;

/**
 * Палитра
 */
public class Palette extends Instrument{

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
    private void initialize(){
        activeColor = Color.BLACK;
        backgroundColor = Color.WHITE;

        run();
    }

    private void run(){

    }

}
