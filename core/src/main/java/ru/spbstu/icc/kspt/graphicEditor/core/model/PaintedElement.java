package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;


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
     * Возврат списка {@link BrushElement#points}: используеся при отрисовке этого элемента
     * и при удалении одного из элементов {@link BrushElement}.
     */
    ArrayList<Point> getPoints();

    Point getCenter();

    double getWidth();

    /**
     * Поиск нарисованного элемента в заданных координатах
     */
    boolean findPoint(Point p);

    /**
     * Операция масштабирования
     * @param kx коэффициент масштабирования по x
     * @param ky коэффициент масштабирования по y
     */
    void scale(double kx, double ky);

    /**
     * Операция сдвига
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    void translate(double dx, double dy);

    /**
     * Операция поворота
     * @param a угол поворота в радианах
     */
    void rotate(double a);
}
