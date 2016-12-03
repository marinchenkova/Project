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

    private Point center;
    private boolean isClosed = false;

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
    public int getWidth(){ return width; }
    public Point getStart(){ return start; }
    public Point getEnd(){ return end; }
    public Point getCenter(){ return center; }

    /**
     * Возвращает мастшабированный объект {@link LineElement},  не изменяя его.
     * @param kx коэффициент по X
     * @param ky коэффициент по Y
     * @return мастшабированный объект {@link LineElement}
     */
    public LineElement getScaled(double kx, double ky){
        LineElement b = this;
        b.scale(kx, ky);
        return b;
    }

    /**
     * Возвращает перемещенный объект {@link LineElement},  не изменяя его.
     * @param dx пермещение по X
     * @param dy пермещение по Y
     * @return перемещенный объект {@link LineElement}
     */
    public LineElement getTranslated(int dx, int dy){
        LineElement b = this;
        b.translate(dx, dy);
        return b;
    }

    /**
     * Возвращает повернутый объект {@link LineElement},  не изменяя его.
     * @param a угол в радианах
     * @return повернутый объект {@link LineElement}
     */
    public LineElement getRotated(double a){
        LineElement b = this;
        b.rotate(a);
        return b;
    }

    /**
     * Поиск геометрического центра для применения в методах преобразования координат.
     * Вызывается всегда после методов изменения {@link BrushElement}.
     */
    private void setCenter(){
        center = new Point(((start.getX() + end.getX()) / 2),
                           ((start.getY() + end.getY()) / 2));
    }

    /**
     * Поиск точек {@link Point} в заданных координатах. Метод должен быть переопределен для
     * каждого инструмента.
     * @param x x
     * @param y y
     * @return true - если точка {@link Point} найдена.
     */
    @Override
    public boolean findPoint(int x, int y){
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
        end = new Point((int) ((end.getX() - start.getX()) * kx + start.getX()),
                        (int) ((end.getY() - start.getY()) * ky + start.getY()));
    }

    /**
     * Перемещение объекта {@link LineElement}.
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    @Override
    public void translate(int dx, int dy){
        start = new Point(start.getX() + dx, start.getY() + dy);
        end = new Point(end.getX() + dx, end.getY() + dy);
    }

    /**
     * Вращение объекта {@link BrushElement} вокруг его геометрического центра.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        int mx = center.getX();
        int my = center.getY();

        int x = (int) ((start.getX() - mx) * Math.cos(a) - (start.getY() - my) * Math.sin(a)) + mx;
        int y = (int) ((start.getX() - mx) * Math.sin(a) + (start.getY() - my) * Math.cos(a)) + my;
        start = new Point(x, y);

        x = (int) ((end.getX() - mx) * Math.cos(a) - (end.getY() - my) * Math.sin(a)) + mx;
        y = (int) ((end.getX() - mx) * Math.sin(a) + (end.getY() - my) * Math.cos(a)) + my;
        end = new Point(x, y);
    }
}
