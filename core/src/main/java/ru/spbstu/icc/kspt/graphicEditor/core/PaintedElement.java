package ru.spbstu.icc.kspt.graphicEditor.core;

import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Интерфейс для объектов, нарисованных одним действием инструмента рисования
 */
public interface PaintedElement{

    /**
     * Возврат списков координат
     */
    ArrayList<Point> getPoints();

    /**
     * Нарисовать объект с заданной шириной линии
     */
    void paintAtom(Point point);

    /**
     * Поиск нарисованного элемента в заданных координатах
     */
    boolean onPainted(int x, int y);

    /**
     * Операция масштабирования
     * @param k коэффициент масштабирования
     */
    void scale(double k);

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
}
