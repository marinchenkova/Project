package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;


/**
 * Интерфейс для объектов, нарисованных одним действием инструмента рисования.
 */
public interface PaintedElement{

    /**
     * Нарисовать объект с заданной шириной линии.
     */
    void addPoint(Point point);

    /**
     * Запрет добавления точек.
     */
    void close();

    /**
     * Поиск нарисованного элемента в заданных координатах
     */
    boolean findPoint(int x, int y);

    /**
     * Операция масштабирования
     * @param kx коэффициент масштабирования
     * @param ky
     */
    void scale(double ky, double kx);

    /**
     * Операция сдвига
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    void translate(int dx, int dy);

    /**
     * Операция поворота
     * @param a угол поворота в радианах
     */
    void rotate(double a);

    /**
     * Возврат ширины элемента
     * @return ширина
     */
    int getWidth();

    /**
     * Возвращает мастшабированный объект {@link PaintedElement},  не изменяя его.
     * @param kx коэффициент по X
     * @param ky коэффициент по Y
     * @return мастшабированный объект {@link PaintedElement}
     */
    PaintedElement getScaled(double kx, double ky);

    /**
     * Возвращает перемещенный объект {@link PaintedElement},  не изменяя его.
     * @param dx пермещение по X
     * @param dy пермещение по Y
     * @return перемещенный объект {@link PaintedElement}
     */
    PaintedElement getTranslated(int dx, int dy);

    /**
     * Возвращает повернутый объект {@link PaintedElement},  не изменяя его.
     * @param a угол в радианах
     * @return повернутый объект {@link PaintedElement}
     */
    PaintedElement getRotated(double a);
}
