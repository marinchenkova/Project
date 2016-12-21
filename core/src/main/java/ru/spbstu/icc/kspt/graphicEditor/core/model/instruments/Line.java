package ru.spbstu.icc.kspt.graphicEditor.core.model.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.model.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.LineElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Инструмент Линия. Обращается с объектом {@link LineElement}.
 */
public class Line extends Instrument {

    public Line(Object buttonIcon){ icon = buttonIcon; }

    /**
     * Применение линии: при вызове метода создается объект {@link LineElement}.
     * @param point добавляемая точка
     * @param width диаметр точки
     */
    @Override
    public void onPressed(Point point, double width){ pe = new LineElement(point, width); }

    /**
     * Применение линии: при вызове метода в существующем объекте {@link LineElement} изменяется
     * конечная точка.
     * @param point новая конечная точка
     * @throws NullPointerException нельзя редактировать {@link LineElement}, т.к.
     * он еще не создан
     */
    @Override
    public void onDragged(Point point) throws NullPointerException{
        if(pe == null) throw new NullPointerException("PaintedElement does not exist");
        pe.addPoint(point);
    }
}
