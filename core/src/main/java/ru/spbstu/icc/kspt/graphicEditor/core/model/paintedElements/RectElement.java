package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * @author Marinchenko V. A.
 */
public class RectElement implements PaintedElement{

    private Point start;
    private Point end;
    private double width;

    private Point center;
    private boolean isClosed = false;
    private ArrayList<Point> points = new ArrayList<>();

    public RectElement(Point point, double w){
        width = w;
        start = point;
        points.add(point);
    }

    /**
     * Добавление точек {@link Point} к списку {@link BrushElement#points}.
     * В первый раз вызывается из конструктора {@link BrushElement#BrushElement(Point, double)},
     * затем должен вызываться при перетаскивании мыши
     * {@link javafx.scene.input.MouseEvent#MOUSE_DRAGGED}.
     * @param point добавляемая точка
     */
    @Override
    public void addPoint(Point point){
 /*       if(!isClosed){
            points.remove(end);
            end = point;
            points.add(end);
            setCenter();

        }
        */
    }

    /**
     * Запрет добавления точек методом {@link LineElement#addPoint(Point)}.
     */
    @Override
    public void close(){
        isClosed = true;
        setCenter();
    }

    @Override
    public Point getCenter(){ return center; }

    @Override
    public double getWidth(){ return  width; }

    /**
     * Поиск геометрического центра для применения в методах преобразования координат.
     */
    private void setCenter(){
 /*       center = new Point(((start.getX() + end.getX()) / 2),
                ((start.getY() + end.getY()) / 2));
                */
    }

    /**
     * Возврат списка {@link LineElement#points}: используеся при отрисовке этого элемента
     * и при удалении одного из элементов {@link LineElement}.
     */
    public ArrayList<Point> getPoints(){ return points; }

    /**
     * Поиск точек {@link Point} в заданных координатах. Метод должен быть переопределен для
     * каждого инструмента.
     * @param p точка {@link Point}
     * @return true - если точка {@link Point} найдена.
     */
    @Override
    public boolean findElement(Point p){
 /*       double x = p.getX();
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
                */
        return false;
    }

    private void updatePoints(){
/*        points = new ArrayList<>();
        points.add(start);
        points.add(end);
        */
    }

    /**
     * Масштабирование объекта {@link LineElement}.
     * @param kx коэффициент масштабирования по оси X
     * @param ky коэффициент масштабирования по оси Y
     */
    @Override
    public void scale(double kx, double ky){
/*       double sx = start.getX();
        double sy = start.getY();
        double ex = end.getX();
        double ey = end.getY();

        if(((Double) kx).isInfinite() || ((Double) kx).isNaN() || kx == 0){
            start = new Point(sx,(sy - center.getY()) * ky + center.getY());
            end = new Point(ex,(ey - center.getY()) * ky + center.getY());
        }
        else if(((Double) ky).isInfinite() || ((Double) ky).isNaN() || ky == 0){
            start = new Point((sx - center.getX()) * kx + center.getX(), sy);
            end = new Point((ex - center.getX()) * kx + center.getX(), ey);

        } else {
            start = new Point((sx - center.getX()) * kx + center.getX(),
                    (sy - center.getY()) * ky + center.getY());
            end = new Point((ex - center.getX()) * kx + center.getX(),
                    (ey - center.getY()) * ky + center.getY());
        }

        updatePoints();
        */
    }

    /**
     * Перемещение объекта {@link LineElement}.
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    @Override
    public void translate(double dx, double dy){
/*        start = new Point(start.getX() + dx, start.getY() + dy);
        end = new Point(end.getX() + dx, end.getY() + dy);
        setCenter();
        updatePoints();
        */
    }

    /**
     * Вращение объекта {@link LineElement} вокруг его геометрического центра.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
/*        double mx = center.getX();
        double my = center.getY();

        double x = (start.getX() - mx) * Math.cos(a) - (start.getY() - my) * Math.sin(a) + mx;
        double y = (start.getX() - mx) * Math.sin(a) + (start.getY() - my) * Math.cos(a) + my;
        start = new Point(x, y);

        x = (end.getX() - mx) * Math.cos(a) - (end.getY() - my) * Math.sin(a) + mx;
        y = (end.getX() - mx) * Math.sin(a) + (end.getY() - my) * Math.cos(a) + my;
        end = new Point(x, y);

        updatePoints();
        */
    }
}
