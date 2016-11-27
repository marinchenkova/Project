package ru.spbstu.icc.kspt.graphicEditor.core;

import java.util.ArrayList;

/**
 * @author Marinchenko V. A.
 */
public class Cache {

    private ArrayList<PaintedElement> state = new ArrayList<>();
    private int count = -1;

    public Cache(){}

    public ArrayList<PaintedElement> getState(){ return state; }

    public void add(PaintedElement element){
        state.add(element);
        count++;
    }

    public PaintedElement getLast(){
        return state.get(count--);
    }

    public void clear(){
        state = new ArrayList<>();
        count = -1;
    }
}
