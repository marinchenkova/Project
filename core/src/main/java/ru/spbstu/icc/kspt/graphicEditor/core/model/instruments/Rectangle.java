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
}
