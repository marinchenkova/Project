package ru.spbstu.icc.kspt.graphicEditor.core.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента кисть
 */
public class BrushElement implements PaintedElement {

    private ArrayList<Point> points = new ArrayList<>();
    private int diameter;

    private int xmax = 0;
    private int ymax = 0;
    private int xmin = 10000;
    private int ymin = 10000;

    public BrushElement(Point point, int d){
        diameter = d;
        addPoint(point);
    }

    /**
     * Добавление точек {@link Point} к списку {@link BrushElement#points}.
     * В первый раз вызывается из конструктора {@link BrushElement#BrushElement(Point, int)},
     * затем должен вызываться при перетаскивании мыши
     * {@link javafx.scene.input.MouseEvent#MOUSE_DRAGGED}.
     * @param point добавляемая точка
     */
    @Override
    public void addPoint(Point point){
        points.add(point);
        extrem();
    }

    /**
     * Поиск максимумов и минимумов для применения в методах преобразования координат.
     * Вызывается всегда после метода {@link BrushElement#addPoint(Point)}.
     */
    private void extrem(){
        if(points.get(points.size() - 1).getX() > xmax) {
            xmax = points.get(points.size() - 1).getX();
        }
        if(points.get(points.size() - 1).getY() > ymax) {
            ymax = points.get(points.size() - 1).getY();
        }
        if(points.get(points.size() - 1).getX() < xmin) {
            xmin = points.get(points.size() - 1).getX();
        }
        if(points.get(points.size() - 1).getY() < ymin) {
            ymin = points.get(points.size() - 1).getY();
        }
    }

    /**
     * Возврат списка {@link BrushElement#points}: используеся при отрисовке этого элемента
     * и при удалении одного из элементов {@link BrushElement}.
     */
    public ArrayList<Point> getPoints(){ return points; }

    public int getDiameter(){ return diameter; }

    /**
     * Поиск точек {@link Point} в заданных координатах. Метод должен быть переопределен для
     * каждого инструмента.
     * @param x x
     * @param y y
     * @return true - если точка {@link Point} найдена.
     */
    @Override
    public boolean findPoint(int x, int y){
        for (Point point : points) {
            if ((Math.abs(x - point.getX()) <= diameter / 2) &&
                    (Math.abs(y - point.getY()) <= diameter / 2)) {
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
        int ax = points.get(0).getX();
        int ay = points.get(0).getY();

        ArrayList<Point> scaledPoints = new ArrayList<>();
        scaledPoints.add(new Point(ax, ay));

        for(int i = 1; i < points.size(); i++){
            int x = (int) ((points.get(i).getX() - ax) * kx + ax);
            int y = (int) ((points.get(i).getY() - ay) * ky + ay);
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
    public void translate(int dx, int dy){
        ArrayList<Point> translatedPoints = new ArrayList<>();

        for (Point p : points) {
            translatedPoints.add(new Point(p.getX() + dx,p.getY() + dy));
        }

        points = translatedPoints;
    }

    /**
     * Вращение объекта {@link BrushElement} вокруг его геометрического центра.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        int mx = (xmax + xmin) / 2;
        int my = (ymax + ymin) / 2;

        ArrayList<Point> rotatedPoints = new ArrayList<>();

        for (Point p : points) {
            int x = (int) ((p.getX() - mx) * Math.cos(a) - (p.getY() - my) * Math.sin(a)) + mx;
            int y = (int) ((p.getX() - mx) * Math.sin(a) + (p.getY() - my) * Math.cos(a)) + my;
            rotatedPoints.add(new Point(x, y));
        }

        points = rotatedPoints;
    }
}
