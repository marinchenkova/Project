package ru.spbstu.icc.kspt.graphicEditor.core.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Инструмент для выполнения действий над объектами {@link PaintedElement}
 */
public class Agent extends Instrument {

    public Agent(Object buttonIcon, Object cursorIcon){
        icon = buttonIcon;
        cursor = cursorIcon;
    }

    @Override
    public void mousePressed(Point point, int diameter){
        //TODO
    }


    @Override
    public void mouseDragged(Point point) throws NullPointerException{
        //TODO
    }
}
