package graphicEditor.instrument.figure;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import graphicEditor.instrument.figure.figures.Ellipse;
import graphicEditor.instrument.figure.figures.Line;
import graphicEditor.instrument.figure.figures.Rectangle;

/**
 * Фигура
 */
public class Figure extends Instrument {
    //Поля
    private Controller controller;

    //Классы - наследники
    private Rectangle rectangle;
    private Ellipse ellipse;
    private Line line;

    //Конструктор
    public Figure() {

    }

    public Figure (Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        rectangle = new Rectangle(controller);
        ellipse = new Ellipse(controller);
        line = new Line(controller);
    }
}
