package ru.spbstu.icc.kspt.graphicEditor.core;

import java.util.ArrayList;

/**
 * Доска
 */
public class Desk {

    private int width;
    private int height;
    private Object backgroundColor;
    private ArrayList<PaintedElement> pe = new ArrayList<>();

    public Desk (int w, int h, Object backCol){
        width = w;
        height = h;
        backgroundColor = backCol;
    }

    /**
     * Поиск номера нарисованного элемента позаданным координатам
     */
    public PaintedElement findPainted(int x, int y) throws NullPointerException{
        if(pe.size() > 0){
            for (PaintedElement element: pe){
                if (element.findPoint(x, y)){
                    return element;
                }
            }
        }
        return null;
    }

    public Object getBackgroundColor(){ return backgroundColor; }

    public int getWidth(){ return width; }

    public int getHeight(){ return height; }

    public ArrayList<PaintedElement> getPE(){ return pe; }

    public void setBackgroundColor(Object backCol){ backgroundColor = backCol; }

    public void setWidth(int w){ width = w; }

    public void setHeight(int h){ height = h; }

    public void addPaintedElement(PaintedElement element){ pe.add(element); }
}

