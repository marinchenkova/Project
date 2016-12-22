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
}
