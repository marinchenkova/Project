package ru.spbstu.icc.kspt.graphicEditor.core.desk;

import ru.spbstu.icc.kspt.graphicEditor.core.paintedElements.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.color.Color;
import ru.spbstu.icc.kspt.graphicEditor.core.util.mouse.MouseEvent;
import ru.spbstu.icc.kspt.graphicEditor.core.util.mouse.MouseEvents;

import java.util.ArrayList;

/**
 * Доска
 */
public class Desk {

    private int width;
    private int height;
    private Color backgroundColor;
    private ArrayList<PaintedElement> pe = new ArrayList<>();

    public Desk (int w, int h, Color backCol){
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
                if (element.onPainted(x, y)){
                    return element;
                }
            }
        }
        return null;
    }

    public Color getBackgroundColor(){ return backgroundColor; }

    public int getWidth(){ return width; }

    public int getHeight(){ return height; }

    public void setBackgroundColor(Color backCol){ backgroundColor = backCol; }

    public void setWidth(int w){ width = w; }

    public void setHeight(int h){ height = h; }

    public void addPaintedElement(PaintedElement element){ pe.add(element); }
}

