package graphicEditor.core.desk;

import graphicEditor.core.instrument.instruments.Agent;
import graphicEditor.core.paintedElements.PaintedElement;
import graphicEditor.core.util.color.Color;
import graphicEditor.core.util.mouse.MouseEvent;
import graphicEditor.core.util.mouse.MouseEvents;

import java.util.ArrayList;

/**
 * Доска
 */
public class Desk {

    private int width;
    private int height;
    private Color backgroundColor;
    private ArrayList<PaintedElement> pe = new ArrayList<>();
    private int elNum = -1;

    public Desk (int w, int h, Color backCol){
        width = w;
        height = h;
        backgroundColor = backCol;
    }

    public void onMouseAction(MouseEvent me){
        MouseEvents event = me.getEvent();
        int x = me.getX();
        int y = me.getY();
        switch (event){
            case ENTERED:
                elNum = findPainted(x, y);
                break;
            case EXITED:

                break;
            case PRESSED:

                break;
            case RELEASED:
                elNum = findPainted(x, y);
                break;
            case DRAGGED:

                break;
            case MOVED:
                elNum = findPainted(x, y);
                break;
        }
    }

    /**
     * Поиск номера нарисованного элемента позаданным координатам
     */
    private int findPainted(int x, int y){
        if(pe.size() > 0){
            for (int i = 0; i < pe.size(); i++){
                if (pe.get(i).onPainted(x, y)){
                    return i;
                }
            }
        }
        return -1;
    }

    public Color getBackgroundColor(){ return backgroundColor; }

    public int getWidth(){ return width; }

    public int getHeight(){ return height; }

    public int getElNum(){ return elNum; }

    public PaintedElement getCurrentElement(){ return pe.get(elNum); }

    public void setBackgroundColor(Color backCol){ backgroundColor = backCol; }

    public void setWidth(int w){ width = w; }

    public void setHeight(int h){ height = h; }

    public void addPaintedElement(PaintedElement element){ pe.add(element); }
}

