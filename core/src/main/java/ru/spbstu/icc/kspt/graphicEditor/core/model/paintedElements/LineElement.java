package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.model.instruments.Line;
import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента {@link Line}.
 */
public class LineElement extends PaintedElement {

    /**
     * Конструктор задает ширину линии {@link LineElement#width} и первую точку
     * {@link LineElement#start}.
     * @param point начальная точка
     * @param w ширина линии
     */
    public LineElement(Point point, double w){
        width = w;
        start = point;
        points.add(point);
    }

    /**
     * Добавление новой точки в список {@link LineElement#points}.
     */
    @Override
    public void addPoint(Point point){
        if(!isClosed){
            points.remove(end);
            end = point;
            points.add(end);
            setCenter();
        }
    }

    /**
     * Обновить список точек {@link LineElement#points}: вызывается после операций преобразования
     * координат.
     */
    @Override
    public void updatePoints(){
        points = new ArrayList<>();
        points.add(start);
        points.add(end);
    }

    /**
     * Поиск элемента {@link LineElement} в заданной точке.
     * @param p заданная точка
     * @return true, если элемент {@link LineElement} найден
     */
    @Override
    public boolean findElement(Point p){
        double x = p.getX();
        double y = p.getY();

        double k = (end.getY() - start.getY()) / (end.getX() - start.getX());
        double w = width / 2;
        double r = w / Math.cos(Math.atan(k));

        return ((x >= Math.min(end.getX(), start.getX()) - w)       &&
               (x <= Math.max(end.getX(), start.getX()) + w)        &&
               (y >= Math.min(end.getY(), start.getY()) - w)        &&
               (y <= Math.max(end.getY(), start.getY()) + w)        &&
               (y <= k * (x - start.getX()) + start.getY() + r) &&
               (y >= k * (x - start.getX()) + start.getY() - r));
    }

    /**
     * Масштабирование объекта {@link LineElement}.
     * @param kx коэффициент масштабирования по оси X
     * @param ky коэффициент масштабирования по оси Y
     */
    @Override
    public void scale(double kx, double ky){
        start = getScaledPoint(start, kx , ky);
        end = getScaledPoint(end, kx , ky);

        updatePoints();
    }

    /**
     * Перемещение объекта {@link LineElement}.
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    @Override
    public void translate(double dx, double dy){
        start = getTranslatedPoint(start, dx, dy);
        end = getTranslatedPoint(end, dx, dy);

        setCenter();

        updatePoints();
    }

    /**
     * Вращение объекта {@link LineElement} вокруг его геометрического центра {@link LineElement#center}.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        start = getRotatedPoint(start, a);
        end = getRotatedPoint(end, a);

        updatePoints();
    }
}
