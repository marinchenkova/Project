package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Объект, нарисованный одним действием инструмента Линия
 */
public class LineElement implements PaintedElement {

    private Point start;
    private Point end;
    private int width;

    private int xmax = 0;
    private int ymax = 0;
    private int xmin = 10000;
    private int ymin = 10000;

    public LineElement(Point point, int d){
        width = d;
        start = point;
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
        end = point;
        xmin = start.getX() < end.getX() ? start.getX() : end.getX();
        xmax = start.getX() > end.getX() ? start.getX() : end.getX();
        ymin = start.getY() < end.getY() ? start.getY() : end.getY();
        ymax = start.getY() > end.getY() ? start.getY() : end.getY();
    }

    @Override
    public int getWidth(){ return width; }

    /**
     * Поиск точек {@link Point} в заданных координатах. Метод должен быть переопределен для
     * каждого инструмента.
     * @param x x
     * @param y y
     * @return true - если точка {@link Point} найдена.
     */
    @Override
    public boolean findPoint(int x, int y){
        double k = (ymax - ymin) / (xmax - xmin);
        double w = width / 2;
        double r = w / Math.cos(Math.atan(k));
        return (x >= xmin - w) && (x <= xmax + w) &&
               (y >= ymin - w) && (y <= ymax + w) &&
               (y <= k * x + r) && (y >= k * x - r);
    }

    /**
     * Масштабирование объекта {@link BrushElement}.
     * @param kx коэффициент масштабирования по оси X
     * @param ky коэффициент масштабирования по оси Y
     */
    @Override
    public void scale(double kx, double ky){
        /*
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
        */
    }

    /**
     * Перемещение объекта {@link BrushElement}.
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    @Override
    public void translate(int dx, int dy){
        /*
        ArrayList<Point> translatedPoints = new ArrayList<>();

        for (Point p : points) {
            translatedPoints.add(new Point(p.getX() + dx,p.getY() + dy));
        }

        points = translatedPoints;
        */
    }

    /**
     * Вращение объекта {@link BrushElement} вокруг его геометрического центра.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        /*
        int mx = (xmax + xmin) / 2;
        int my = (ymax + ymin) / 2;

        ArrayList<Point> rotatedPoints = new ArrayList<>();

        for (Point p : points) {
            int x = (int) ((p.getX() - mx) * Math.cos(a) - (p.getY() - my) * Math.sin(a)) + mx;
            int y = (int) ((p.getX() - mx) * Math.sin(a) + (p.getY() - my) * Math.cos(a)) + my;
            rotatedPoints.add(new Point(x, y));
        }

        points = rotatedPoints;
        */
    }
}
