package graphicEditor.instrument.figures;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import graphicEditor.instrument.figures.figures.*;
import javafx.scene.*;
import javafx.scene.image.Image;

//TODO комментарии

/**
 * Фигура
 */
public class Figure extends Instrument {
    /**
     * Поля
     */

    /**
     * Класс - контроллер
     */
    private Controller controller;

    /**
     * Классы - наследники
     */
    private Rectangle rectangle;
    private Ellipse ellipse;
    private Line line;

    protected static Cursor figureCursor = new ImageCursor(new Image("/images/cursors/figureCursor.png"), 2, 2);

    /**
     * Конструктор
     */
    public Figure() {

    }

    public Figure (Controller controller) {
        this.controller = controller;
        initialize();
    }

    //TODO хорошо бы аннотации добавлять, когда метод перегружается.
    //TODO хотя, возможно, после рефакторинга не будет тут такого

    /**
     * Инициализация
     */
    public void initialize(){
        rectangle = new Rectangle(controller);
        ellipse = new Ellipse(controller);
        line = new Line(controller);
    }
}
