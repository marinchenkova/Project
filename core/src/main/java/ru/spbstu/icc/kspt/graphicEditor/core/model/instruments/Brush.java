package ru.spbstu.icc.kspt.graphicEditor.core.model.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.model.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Кисть
 */
public class Brush extends Instrument {

    public Brush(Object buttonIcon, Object cursorIcon){
        icon = buttonIcon;
        cursor = cursorIcon;
    }

    /**
     * Применение кисти: при вызове метода создается объект {@link BrushElement}.
     * @param point добавляемая точка {@link Point}
     * @param width диаметр точки {@link Point}
     */
    @Override
    public void onPressed(Point point, double width){
        pe = new BrushElement(point, width);
    }

    /**
     * Применение кисти: при вызове метода в существующий объект {@link BrushElement}
     * добавляется точка
     * @param point добавляемая точка
     * @throws NullPointerException нельзя редактировать {@link PaintedElement}, т.к.
     * он еще не создан
     */
    @Override
    public void onDragged(Point point) throws NullPointerException{
        if(pe == null) throw new NullPointerException("PaintedElement does not exist");
        pe.addPoint(point);
    }


}
