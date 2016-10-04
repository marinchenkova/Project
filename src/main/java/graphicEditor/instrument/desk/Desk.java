package graphicEditor.instrument.desk;

import graphicEditor.Controller;
import graphicEditor.MainApp;
import graphicEditor.instrument.Instrument;
import javafx.scene.canvas.Canvas;


/**
 * Доска
 */
public class Desk extends Instrument {
    /**
     * Поля
     */

    /**
     * Класс - контроллер
     */
    private Controller controller;

    /**
     * Объект в FXML
     */
    public Canvas deskCanvas;

    //Конструктор
    public Desk (){

    }

    public Desk (Controller controller){
        this.controller = controller;
        initialize();
    }

    public  void initialize(){
        deskCanvas = controller.desk;
    }
}
