package ru.spbstu.icc.kspt.graphicEditor.core.model.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.model.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.RectElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Инструмент Прямоугольник. Обращается с объектом {@link RectElement}.
 */
public class Rectangle extends Instrument{

    public Rectangle(Object buttonIcon){ icon = buttonIcon; }

    /**
     * Применение прямоугольника: при вызове метода создается объект {@link RectElement}.
     * @param point добавляемая точка
     * @param width диаметр точки
     */
    @Override
    public void onPressed(Point point, double width){ pe = new RectElement(point, width); }

    /**
     * Применение линии: при вызове метода в существующем объекте {@link RectElement} изменяется
     * конечная точка.
     * @param point новая конечная точка
     * @throws NullPointerException нельзя редактировать {@link RectElement}, т.к.
     * он еще не создан
     */
    @Override
    public void onDragged(Point point) throws NullPointerException{
        if(pe == null) throw new NullPointerException("PaintedElement does not exist");
        pe.addPoint(point);
    }
}
