package ru.spbstu.icc.kspt.graphicEditor.core.model.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.model.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Инструмент для выполнения действий над объектами {@link PaintedElement}
 */
public class Agent {

    private Object icon;
    private Object cursor;

    public Agent(Object buttonIcon, Object cursorIcon){
        icon = buttonIcon;
        cursor = cursorIcon;
    }

    public void scale(PaintedElement pe, double kx, double ky){
        pe.scale(kx, ky);
    }

    public void translate(PaintedElement pe, int dx, int dy){
        pe.translate(dx, dy);
    }

    public void rotate(PaintedElement pe, double a){
        pe.rotate(a);
    }

    public Object getIcon() throws NullPointerException{ return icon; }

    public Object getCursor() throws NullPointerException{ return cursor; }
}
