package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Интерфейс для объектов, нарисованных одним действием инструмента рисования
 */
public interface PaintedElement{

    /**
     * Нарисовать объект с заданной шириной линии
     */
    void addPoint(Point point);

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
}
