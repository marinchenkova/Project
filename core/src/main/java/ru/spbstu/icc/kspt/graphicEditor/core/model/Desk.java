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

    private ArrayList<PaintedElement> elements = new ArrayList<>();

    public Desk (int w, int h, Object backCol){
        width = w;
        height = h;
        backgroundColor = backCol;
    }

    /**
     * Поиск номера нарисованного элемента позаданным координатам
     */
    public PaintedElement findPainted(Point point) throws NullPointerException{
        if(elements.size() > 0){
            for (PaintedElement e : elements){
                if (e.findElement(point)){
                    elements.remove(e);
                    return e;
                }
            }
        }

        return null;
    }

    public void addElement(PaintedElement element){ elements.add(element); }

    public ArrayList<PaintedElement> getElements() throws NullPointerException{ return elements; }

    public Object getBackgroundColor(){ return backgroundColor; }

    public int getWidth(){ return width; }

    public int getHeight(){ return height; }

    public String getSizeString(){ return width + ", " + height; }

    public void setElements(ArrayList<PaintedElement> e){ elements = e; }

    public void setBackgroundColor(Object backCol){ backgroundColor = backCol; }

    public void setWidth(int w){ width = w; }

    public void setHeight(int h){ height = h; }

    public void setSize(int w, int h){
        setWidth(w);
        setHeight(h);
    }
}

