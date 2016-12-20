package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;
import java.util.ArrayList;


/**
 * Абстрактный класс {@link PaintedElement} служит для реализации объектов, выполненных единичным действием
 * инструмента рисования. Такой объект содержит следующую информацию: список точек ({@link Point})
 * {@link PaintedElement#points}, геометрический центр {@link PaintedElement#center}, ширину линии
 * {@link PaintedElement#width}.
 */
public abstract class PaintedElement{

    protected ArrayList<Point> points = new ArrayList<>();

    protected Point start;
    protected Point end;
    protected Point center;

    protected double width;
    protected boolean isClosed = false;


    /**
     * Добавление новой точки в список {@link PaintedElement#points}.
     */
    public abstract void addPoint(Point point);

    /**
     * Обновить список точек {@link PaintedElement#points}: вызывается после операций преобразования
     * координат.
     */
    public void updatePoints(){}

    /**
     * Запрет добавления точек и поиск геометрического центра: метод должен быть вызван после
     * завершения рисования элемента {@link PaintedElement}.
     */
    public void close(){
        isClosed = true;
        setCenter();
    }

    /**
     * Поиск элемента {@link PaintedElement} в заданной точке.
     */
    public abstract boolean findElement(Point p);

    /**
     * Операция масштабирования.
     * @param kx коэффициент масштабирования по x
     * @param ky коэффициент масштабирования по y
     */
    public abstract void scale(double kx, double ky);

    /**
     * Операция сдвига.
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    public abstract void translate(double dx, double dy);

    /**
     * Операция поворота.
     * @param a угол поворота в радианах
     */
    public abstract void rotate(double a);

    /**
     * Перемещение точки относительно геометрического центра {@link PaintedElement#center}.
     * @param point перемещаемая точка
     * @param dx перемещение по x
     * @param dy перемещение по y
     * @return перемещенная точка
     */
    public Point getTranslatedPoint(Point point, double dx, double dy){
        return new Point(point.getX() + dx, point.getY() + dy);
    }

    /**
     * Поворот точки относительно геометрического центра {@link PaintedElement#center}.
     * @param point поворачиваемая точка
     * @param a угол поворота
     * @return повернутая точка
     */
    public Point getRotatedPoint(Point point, double a){
        double mx = center.getX();
        double my = center.getY();

        double x = (point.getX() - mx) * Math.cos(a) - (point.getY() - my) * Math.sin(a) + mx;
        double y = (point.getX() - mx) * Math.sin(a) + (point.getY() - my) * Math.cos(a) + my;

        return new Point(x, y);
    }

    /**
     * Масштабирование точки относительно геометрического центра {@link PaintedElement#center}.
     * @param point масштабируемая точка
     * @param kx коэффициент масштабирования по x
     * @param ky коэффициент масштабирования по y
     * @return масштабированная точка
     */
    public Point getScaledPoint(Point point, double kx, double ky){
        double x = point.getX();
        double y = point.getY();

        double sx = x;
        double sy = y;

        if(!((Double) kx).isInfinite() && !((Double) kx).isNaN() && kx != 0){
            sx = (x - center.getX()) * kx + center.getX();
        }

        if(!((Double) ky).isInfinite() && !((Double) ky).isNaN() && ky != 0){
            sy = (y - center.getY()) * ky + center.getY();
        }

        return new Point(sx, sy);
    }

    /**
     * Возврат списка точек {@link PaintedElement#points}: используеся при отрисовке
     * данного элемента и при удалении одного из элементов {@link PaintedElement}.
     * @return список точек
     */
    public ArrayList<Point> getPoints(){ return points; }

    /**
     * Возврат геометрического центра {@link PaintedElement#center}.
     * @return геометрический центр
     */
    public Point getCenter(){ return center; }

    /**
     * Возврат ширины элемента {@link PaintedElement#width}.
     * @return ширина
     */
    public double getWidth(){ return width; }

    /**
     * Обновление геометрического центра {@link PaintedElement#center}: применяется в методах
     * преобразования координат.
     */
    public void setCenter(){
        center = new Point(((start.getX() + end.getX()) / 2),
                           ((start.getY() + end.getY()) / 2));
    }
}
