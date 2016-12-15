package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Доска
 */
public class Desk {

    private int width;
    private int height;
    private Object backgroundColor;
    private ArrayList<PaintedElement> pEls = new ArrayList<>();

    public Desk (int w, int h, Object backCol){
        width = w;
        height = h;
        backgroundColor = backCol;
    }

    /**
     * Поиск номера нарисованного элемента позаданным координатам
     */
    public PaintedElement findPainted(Point point){
        try{
            if(pEls.size() > 0){
                for (PaintedElement element: pEls){
                    if (element.findPoint(point)){
                        pEls.remove(element);
                        return element;
                    }
                }
            }
        } catch (NullPointerException e){
            System.err.println("Desk.findPainted() catch " + e);
        }
        return null;
    }

    public Object getBackgroundColor(){ return backgroundColor; }

    public int getWidth(){ return width; }

    public int getHeight(){ return height; }

    public String getSizeString(){ return width + ", " + height; }

    public ArrayList<PaintedElement> getPE() throws NullPointerException{ return pEls; }

    public void setBackgroundColor(Object backCol){ backgroundColor = backCol; }

    public void setWidth(int w){ width = w; }

    public void setHeight(int h){ height = h; }

    public void setSize(int w, int h){
        setWidth(w);
        setHeight(h);
    }

    public void addElement(PaintedElement element){ pEls.add(element); }
}

