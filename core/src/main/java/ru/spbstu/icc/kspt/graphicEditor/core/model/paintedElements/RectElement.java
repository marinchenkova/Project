package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.model.instruments.Rectangle;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента {@link Rectangle}.
 */
public class RectElement extends PaintedElement{

    //Угол поворота в радианах
    private double angle = 0;

    private Point pa;
    private Point pb;

    /**
     * Конструктор задает ширину линии {@link RectElement#width} и первую точку
     * {@link RectElement#start}.
     * @param point начальная точка
     * @param w ширина линии
     */
    public RectElement(Point point, double w){
        width = w;
        start = point;
        points.add(start);
    }

    /**
     * Добавление новой точки в список {@link RectElement#points}.
     */
    @Override
    public void addPoint(Point point){
        if(!isClosed){
            end = point;
            pa = new Point(start.getX(), end.getY());
            pb = new Point(end.getX(), start.getY());

            setCenter();
            updatePoints();
        }
    }

    /**
     * Обновить список точек {@link RectElement#points}: вызывается после операций преобразования
     * координат.
     */
    @Override
    public void updatePoints(){
        points = new ArrayList<>();
        points.add(start);
        points.add(pa);
        points.add(end);
        points.add(pb);
        points.add(start);
    }

    /**
     * Поиск элемента {@link RectElement} в заданной точке.
     * @param p заданная точка
     * @return true, если элемент {@link RectElement} найден
     */
    @Override
    public boolean findElement(Point p){
        //Перенесение точки в систему координат, где прямоугольник не повернут
        p = getRotatedPoint(p, -angle);
        double x = p.getX();
        double y = p.getY();

        //Перенесение прямоугольника в систему координат, где прямоугольник не повернут
        Point s = getRotatedPoint(start, -angle);
        Point e = getRotatedPoint(end, -angle);

        double sx = s.getX();
        double sy = s.getY();

        double ex = e.getX();
        double ey = e.getY();



        double w = width / 2;

        //Условия нахождения точки на сторонах прямоугольника с учетом ширины линии
        boolean outBorder = (x >= Math.min(ex, sx) - w) &&
                            (x <= Math.max(ex, sx) + w) &&
                            (y >= Math.min(ey, sy) - w) &&
                            (y <= Math.max(ey, sy) + w);

        boolean inBorder = (x <= Math.min(ex, sx) + w) ||
                           (x >= Math.max(ex, sx) - w) ||
                           (y <= Math.min(ey, sy) + w) ||
                           (y >= Math.max(ey, sy) - w);

        return inBorder && outBorder;
    }

    /**
     * Масштабирование объекта {@link RectElement}.
     * @param kx коэффициент масштабирования по оси X
     * @param ky коэффициент масштабирования по оси Y
     */
    @Override
    public void scale(double kx, double ky){
        start = getScaledPoint(start, kx , ky);
        end = getScaledPoint(end, kx , ky);

        pa = getScaledPoint(pa, kx , ky);
        pb = getScaledPoint(pb, kx , ky);

        updatePoints();
    }

    /**
     * Перемещение объекта {@link RectElement}.
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    @Override
    public void translate(double dx, double dy){
        start = getTranslatedPoint(start, dx, dy);
        end = getTranslatedPoint(end, dx, dy);

        pa = getTranslatedPoint(pa, dx, dy);
        pb = getTranslatedPoint(pb, dx, dy);

        setCenter();

        updatePoints();
    }

    /**
     * Вращение объекта {@link RectElement} вокруг его геометрического центра {@link RectElement#center}.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        start = getRotatedPoint(start, a);
        end = getRotatedPoint(end, a);

        pa = getRotatedPoint(pa, a);
        pb = getRotatedPoint(pb, a);

        angle += a;

        updatePoints();
    }
}
