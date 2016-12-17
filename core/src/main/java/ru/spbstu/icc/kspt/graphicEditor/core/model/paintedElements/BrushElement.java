package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента Кисть
 */
public class BrushElement implements PaintedElement {

    private ArrayList<Point> points = new ArrayList<>();
    private double width;

    private Point center;
    private boolean isClosed = false;

    public BrushElement(Point point, double w){
        width = w;
        addPoint(point);
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
        if(!isClosed) points.add(point);
    }

    /**
     * Запрет добавления точек методом {@link BrushElement#addPoint(Point)}.
     */
    @Override
    public void close(){
        isClosed = true;
        setCenter();
    }

    /**
     * Поиск точек {@link Point} в заданных координатах. Метод должен быть переопределен для
     * каждого инструмента.
     * @param p точка {@link Point}
     * @return true - если точка {@link Point} найдена.
     */
    @Override
    public boolean findElement(Point p){
        for (Point point : points) {
            if ((Math.abs(p.getX() - point.getX()) <= width / 2) &&
                    (Math.abs(p.getY() - point.getY()) <= width / 2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Масштабирование объекта {@link BrushElement}.
     * @param kx коэффициент масштабирования по оси X
     * @param ky коэффициент масштабирования по оси Y
     */
    @Override
    public void scale(double kx, double ky){
        double mx = center.getX();
        double my = center.getY();
        ArrayList<Point> scaledPoints = new ArrayList<>();

        for (Point point : points) {
            double x = (point.getX() - mx) * kx + mx;
            double y = (point.getY() - my) * ky + my;
            scaledPoints.add(new Point(x, y));
        }

        points = scaledPoints;
    }

    /**
     * Перемещение объекта {@link BrushElement}.
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    @Override
    public void translate(double dx, double dy){
        ArrayList<Point> translatedPoints = new ArrayList<>();

        for (Point p : points) {
            translatedPoints.add(new Point(p.getX() + dx,p.getY() + dy));
        }

        points = translatedPoints;
        setCenter();
    }

    /**
     * Вращение объекта {@link BrushElement} вокруг его геометрического центра.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        ArrayList<Point> rotatedPoints = new ArrayList<>();
        double mx = center.getX();
        double my = center.getY();

        for (Point p : points) {
            double x = (p.getX() - mx) * Math.cos(a) - (p.getY() - my) * Math.sin(a) + mx;
            double y = (p.getX() - mx) * Math.sin(a) + (p.getY() - my) * Math.cos(a) + my;
            rotatedPoints.add(new Point(x, y));
        }

        points = rotatedPoints;
    }

    /**
     * Возврат списка {@link BrushElement#points}: используеся при отрисовке этого элемента
     * и при удалении одного из элементов {@link BrushElement}.
     */
    public ArrayList<Point> getPoints(){ return points; }

    @Override
    public Point getCenter(){ return center; }

    @Override
    public double getWidth(){ return width; }

    /**
     * Поиск геометрического центра для применения в методах преобразования координат.
     * Вызывается всегда после методов изменения {@link BrushElement}.
     */
    private void setCenter(){
        double xmax = 0;
        double ymax = 0;
        double xmin = 10000;
        double ymin = 10000;

        for (Point point : points) {
            xmax = point.getX() > xmax ? point.getX() : xmax;
            ymax = point.getY() > ymax ? point.getY() : ymax;
            xmin = point.getX() < xmin ? point.getX() : xmin;
            ymin = point.getY() < ymin ? point.getY() : ymin;
        }

        center = new Point((xmax + xmin) / 2, (ymax + ymin) / 2);
    }
}
