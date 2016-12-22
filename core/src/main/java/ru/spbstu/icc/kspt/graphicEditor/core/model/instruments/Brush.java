package ru.spbstu.icc.kspt.graphicEditor.core.model.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.model.*;
import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Инструмент Кисть. Обращается с объектом {@link BrushElement}.
 */
public class Brush extends Instrument{

    public Brush(Object buttonIcon){ icon = buttonIcon; }

    /**
     * Применение кисти: при вызове метода создается объект {@link BrushElement}.
     * @param point добавляемая точка
     * @param width диаметр точки
     */
    @Override
    public void onPressed(Point point, double width){ pe = new BrushElement(point, width); }
}
