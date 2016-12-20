package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.model.instruments.Brush;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента {@link Brush}.
 */
public class BrushElement extends PaintedElement {

    /**
     * Конструктор задает ширину линии {@link BrushElement#width} и первую точку в списке
     * {@link BrushElement#points}.
     * @param point начальная точка
     * @param w ширина линии
     */
    public BrushElement(Point point, double w){
        width = w;
        addPoint(point);
    }

    /**
     * Добавление новой точки в список {@link BrushElement#points}.
     */
    @Override
    public void addPoint(Point point){ if(!isClosed) points.add(point); }

    /**
     * Поиск элемента {@link BrushElement} в заданной точке.
     * @param p заданная точка
     * @return true, если элемент {@link BrushElement} найден
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
        ArrayList<Point> scaledPoints = new ArrayList<>();

        for (Point point : points) {
            scaledPoints.add(getScaledPoint(point, kx, ky));
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
            translatedPoints.add(getTranslatedPoint(p, dx, dy));
        }

        points = translatedPoints;

        setCenter();
    }

    /**
     * Вращение объекта {@link BrushElement} вокруг его геометрического центра {@link BrushElement#center}.
     * @param a угол поворота в радианах
     */
    @Override
    public void rotate(double a){
        ArrayList<Point> rotatedPoints = new ArrayList<>();

        for (Point p : points) {
            rotatedPoints.add(getRotatedPoint(p, a));
        }

        points = rotatedPoints;
    }

    /**
     * Обновление геометрического центра {@link BrushElement#center}: применяется в методах
     * преобразования координат.
     */
    @Override
    public void setCenter(){
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
