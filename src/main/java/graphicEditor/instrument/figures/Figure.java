package graphicEditor.instrument.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import graphicEditor.instrument.figures.figures.*;
import javafx.scene.*;
import javafx.scene.image.Image;

/**
 * Фигура
 */
public class Figure extends Instrument {

    private Controller controller;
    protected static Cursor figureCursor = new ImageCursor(new Image("/images/cursors/figureCursor.png"), 2, 2);

    /**
     * Конструктор
     */
    public Figure (Controller controller) {
        this.controller = controller;
        initialize();
    }
    public Figure(){

    }

    /**
     * Инициализация
     */
    private void initialize(){
        new Rectangle(controller);
        new Ellipse(controller);
        new Line(controller);
    }
}
