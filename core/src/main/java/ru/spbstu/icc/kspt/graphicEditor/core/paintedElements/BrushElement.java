package ru.spbstu.icc.kspt.graphicEditor.core.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;
import java.util.Collections;

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
        paintAtom(point);
    }

    /**
     * Добавление новых координат к списку
     */
    @Override
    public void paintAtom(Point point){
        points.add(point);
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
     * Возврат списков координат: используеся при отрисовке этого элемента и при удалении одного из элементов
     * {@link PaintedElement}.
     */
    public ArrayList<Point> getPoints(){ return points; }
    public int getDiameter(){ return diameter; }

    @Override
    public boolean onPainted(int x, int y){
        for (int i = 0; i < points.size(); i++) {
            if ((Math.abs(x - points.get(i).getX()) <= diameter / 2) &&
                       (Math.abs(y - points.get(i).getY()) <= diameter / 2)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void scale(double k){
        int ax = points.get(0).getX();
        int ay = points.get(0).getY();

        Point first = new Point(ax, ay);
        ArrayList<Point> scaledPoints = new ArrayList<>();

        scaledPoints.add(first);

        for(int i = 1; i < points.size(); i++){
            Point next = new Point((int) ((points.get(i).getX() - ax) * k) + ax,
                    (int) ((points.get(i).getY() - ay) * k) + ay);
            scaledPoints.add(next);
        }

        points = scaledPoints;
    }

    @Override
    public void translate(int dx, int dy){
        ArrayList<Point> translatedPoints = new ArrayList<>();

        for(int i = 0; i < points.size(); i++){
            Point next = new Point(points.get(i).getX() + dx,
                    points.get(i).getY() + dy);
            translatedPoints.add(next);
        }

        points = translatedPoints;
    }

    @Override
    public void rotate(double a){
        int mx = (int) (xmax - xmin) / 2;
        int my = (int) (ymax - ymin) / 2;
        ArrayList<Point> rotatedPoints = new ArrayList<>();

        for(int i = 0; i < points.size(); i++){
            Point next = new Point((int) ((points.get(i).getX() - mx) * Math.cos(a) -
                        (points.get(i).getY() - my) * Math.sin(a)) + mx,
                (int) ((points.get(i).getX() - mx) * Math.sin(a) +
                        (points.get(i).getY() - my) * Math.cos(a)) + my);
            rotatedPoints.add(next);
        }

        points = rotatedPoints;
    }
}
