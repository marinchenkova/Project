package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента Кисть
 */
public class BrushElement implements PaintedElement {

    private ArrayList<Point> points = new ArrayList<>();
    private int width;

    private Point center;
    private boolean isClosed = false;

    public BrushElement(Point point, int d){
        width = d;
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
     * Поиск геометрического центра для применения в методах преобразования координат.
     * Вызывается всегда после методов изменения {@link BrushElement}.
     */
    private void setCenter(){
        double x = 0;
        double y = 0;

        for (int i = 0; i < points.size(); i++){
            x = (x + points.get(i).getX()) / (i + 1);
            y = (y + points.get(i).getY()) / (i + 1);
        }

        center = new Point((int) x, (int) y);
    }

    /**
     * Возврат списка {@link BrushElement#points}: используеся при отрисовке этого элемента
     * и при удалении одного из элементов {@link BrushElement}.
     */
    public ArrayList<Point> getPoints(){ return points; }

    @Override
    public int getWidth(){ return width; }
    public Point getCenter(){ return center; }

    /**
     * Возвращает мастшабированный объект {@link BrushElement},  не изменяя его.
     * @param kx коэффициент по X
     * @param ky коэффициент по Y
     * @return мастшабированный объект {@link BrushElement}
     */
    public BrushElement getScaled(double kx, double ky){
        BrushElement b = this;
        b.scale(kx, ky);
        return b;
    }

    /**
     * Возвращает перемещенный объект {@link BrushElement},  не изменяя его.
     * @param dx пермещение по X
     * @param dy пермещение по Y
     * @return перемещенный объект {@link BrushElement}
     */
    public BrushElement getTranslated(int dx, int dy){
        BrushElement b = this;
        b.translate(dx, dy);
        return b;
    }

    /**
     * Возвращает повернутый объект {@link BrushElement},  не изменяя его.
     * @param a угол в радианах
     * @return повернутый объект {@link BrushElement}
     */
    public BrushElement getRotated(double a){
        BrushElement b = this;
        b.rotate(a);
        return b;
    }

    /**
     * Поиск точек {@link Point} в заданных координатах. Метод должен быть переопределен для
     * каждого инструмента.
     * @param x координата x
     * @param y координата y
     * @return true - если точка {@link Point} найдена.
     */
    @Override
    public boolean findPoint(int x, int y){
        for (Point point : points) {
            if ((Math.abs(x - point.getX()) <= width / 2) &&
                    (Math.abs(y - point.getY()) <= width / 2)) {
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
        setCenter();
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
        setCenter();
    }

    /**
     * Вращение объекта {@link BrushElement} вокруг его геометрического центра.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        ArrayList<Point> rotatedPoints = new ArrayList<>();
        int mx = center.getX();
        int my = center.getY();

        for (Point p : points) {
            int x = (int) ((p.getX() - mx) * Math.cos(a) - (p.getY() - my) * Math.sin(a)) + mx;
            int y = (int) ((p.getX() - mx) * Math.sin(a) + (p.getY() - my) * Math.cos(a)) + my;
            rotatedPoints.add(new Point(x, y));
        }

        points = rotatedPoints;
        setCenter();
    }
}
