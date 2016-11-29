package ru.spbstu.icc.kspt.graphicEditor.core.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Кисть
 */
public class Brush extends Instrument {

    public Brush(){}

    public Brush(Object buttonIcon, Object cursorIcon){
        icon = buttonIcon;
        cursor = cursorIcon;
    }


    /**
     * Применение кисти
     */
    @Override
    public void onAction(Point point, int diameter){
        int x = point.getX();
        int y = point.getY();
        pe = new BrushElement(point, diameter);

        if(isDragged){
            pe.paintAtom(point);
        }
    }
}
