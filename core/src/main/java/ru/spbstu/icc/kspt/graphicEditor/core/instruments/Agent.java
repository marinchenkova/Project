package ru.spbstu.icc.kspt.graphicEditor.core.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.PaintedElement;

/**
 * Инструмент для выполнения действий над объектами {@link PaintedElement}
 */
public class Agent extends Instrument {

    public Agent(){}
    public Agent(Object buttonIcon, Object cursorIcon){
        icon = buttonIcon;
        cursor = cursorIcon;
    }

    public PaintedElement getScaled(PaintedElement element, double k){
        element.scale(k);
        return element;
    }

    public PaintedElement getTranslated(PaintedElement element, int dx, int dy){
        element.translate(dx, dy);
        return element;
    }

    public PaintedElement getRotated(PaintedElement element, double a){
        element.rotate(a);
        return element;
    }
}
