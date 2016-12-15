package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента Линия
 */
public class LineElement implements PaintedElement {

    private Point start;
    private Point end;
    private int width;

    private Point center;
    private boolean isClosed = false;

    public LineElement(Point point, int d){
        width = d;
        start = point;
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
        if(!isClosed) end = point;
        close();
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
     * Вызывается всегда после методов изменения {@link BrushElement}.
     */
    private void setCenter(){
        center = new Point(((start.getX() + end.getX()) / 2),
                           ((start.getY() + end.getY()) / 2));
    }

    /**
     * Возврат списка {@link BrushElement#points}: используеся при отрисовке этого элемента
     * и при удалении одного из элементов {@link BrushElement}.
     */
    public ArrayList<Point> getPoints(){ return new ArrayList<>(); }

    public Point getStart(){ return start; }
    public Point getEnd(){ return end; }

    /**
     * Поиск точек {@link Point} в заданных координатах. Метод должен быть переопределен для
     * каждого инструмента.
     * @param p точка {@link Point}
     * @return true - если точка {@link Point} найдена.
     */
    @Override
    public boolean findPoint(Point p){
        double x = p.getX();
        double y = p.getY();

        double k = (end.getY() - start.getY()) / (end.getX() - start.getX());
        double w = width / 2;
        double r = w / Math.cos(Math.atan(k));

        return (x >= Math.min(end.getX(), start.getX()) - w) &&
               (x <= Math.max(end.getX(), start.getX()) + w) &&
               (y >= Math.min(end.getY(), start.getY()) - w) &&
               (y <= Math.max(end.getY(), start.getY()) + w) &&
               (y <= k * x + r) && (y >= k * x - r);
    }

    /**
     * Масштабирование объекта {@link LineElement}.
     * @param kx коэффициент масштабирования по оси X
     * @param ky коэффициент масштабирования по оси Y
     */
    @Override
    public void scale(double kx, double ky){
        end = new Point((end.getX() - start.getX()) * kx + start.getX(),
                        (end.getY() - start.getY()) * ky + start.getY());
    }

    /**
     * Перемещение объекта {@link LineElement}.
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    @Override
    public void translate(double dx, double dy){
        start = new Point(start.getX() + dx, start.getY() + dy);
        end = new Point(end.getX() + dx, end.getY() + dy);
    }

    /**
     * Вращение объекта {@link BrushElement} вокруг его геометрического центра.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        double mx = center.getX();
        double my = center.getY();

        double x = (start.getX() - mx) * Math.cos(a) - (start.getY() - my) * Math.sin(a) + mx;
        double y = (start.getX() - mx) * Math.sin(a) + (start.getY() - my) * Math.cos(a) + my;
        start = new Point(x, y);

        x = (end.getX() - mx) * Math.cos(a) - (end.getY() - my) * Math.sin(a) + mx;
        y = (end.getX() - mx) * Math.sin(a) + (end.getY() - my) * Math.cos(a) + my;
        end = new Point(x, y);
    }
}
