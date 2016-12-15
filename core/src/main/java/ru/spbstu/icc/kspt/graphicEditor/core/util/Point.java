package ru.spbstu.icc.kspt.graphicEditor.core.util;

/**
 * Точка
 */
public class Point {

    private double x;
    private double y;

    public Point(double mx, double my){
        x = mx;
        y = my;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    @Override
    public String toString(){
        return (int) x + " ; " + (int) y;
    }
}
