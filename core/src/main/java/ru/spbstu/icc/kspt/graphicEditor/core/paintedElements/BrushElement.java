package ru.spbstu.icc.kspt.graphicEditor.core.paintedElements;

import ru.spbstu.icc.kspt.graphicEditor.core.PaintedElement;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Объект, нарисованный одним действием инструмента кисть
 */
public class BrushElement implements PaintedElement {

    private int lineWidth;
    private ArrayList<Integer> xList = new ArrayList<>();
    private ArrayList<Integer> yList = new ArrayList<>();

    public BrushElement(int x, int y, int width){
        lineWidth = width;
        paintAtom(x, y);
    }

    /**
     * Добавление новых координат к списку
     */
    @Override
    public void paintAtom(int x, int y){
        xList.add(x);
        yList.add(y);
    }

    /**
     * Возврат списков координат: используеся при отрисовке этого элемента и при удалении одного из элементов
     * {@link PaintedElement}.
     */
    public ArrayList<Integer> getXList(){ return xList; }
    public ArrayList<Integer> getYList(){ return yList; }

    @Override
    public boolean onPainted(int x, int y){
        for (int i = 0; i < xList.size(); i++) {
            if ((Math.abs(x - xList.get(i)) <= lineWidth / 2) &&
                       (Math.abs(y - yList.get(i)) <= lineWidth / 2)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void scale(double k){
        int ax = xList.get(0);
        int ay = yList.get(0);
        ArrayList<Integer> newX = new ArrayList<>();
        ArrayList<Integer> newY = new ArrayList<>();

        newX.add(ax);
        newY.add(ay);

        for(int i = 1; i < xList.size(); i++){
            newX.add((int) ((xList.get(i) - ax) * k + ax));
            newY.add((int) ((yList.get(i) - ay) * k + ay));
        }

        xList = newX;
        yList = newY;
    }

    @Override
    public void translate(int dx, int dy){
        ArrayList<Integer> newX = new ArrayList<>();
        ArrayList<Integer> newY = new ArrayList<>();

        for(int i = 0; i < xList.size(); i++){
            newX.add(xList.get(i) + dx);
            newY.add(yList.get(i) - dy);
        }

        xList = newX;
        yList = newY;
    }

    @Override
    public void rotate(double a){
        int mx = (Collections.max(xList) + Collections.min(xList)) / 2;
        int my = (Collections.max(yList) + Collections.min(yList)) / 2;
        ArrayList<Integer> newX = new ArrayList<>();
        ArrayList<Integer> newY = new ArrayList<>();

        for(int i = 0; i < xList.size(); i++){
            newX.add((int) ((xList.get(i) - mx) * Math.cos(a) - (yList.get(i) - my) * Math.sin(a)) + mx);
            newY.add((int) ((xList.get(i) - mx) * Math.sin(a) + (yList.get(i) - my) * Math.cos(a)) + my);
        }

        xList = newX;
        yList = newY;
    }
}
