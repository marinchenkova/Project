package graphicEditor.core.desk;

import graphicEditor.core.paintedElements.PaintedElement;
import graphicEditor.core.util.color.Colors;

import java.util.ArrayList;

/**
 * Доска
 */
public class Desk {

    private int width;
    private int height;
    private Colors backgroundColor;
    private ArrayList<PaintedElement> pe = new ArrayList<>();

    public Desk (int w, int h, Colors backCol){
        width = w;
        height = h;
        backgroundColor = backCol;
    }

    /**
     * Поиск номера нарисованного элемента позаданным координатам
     */
    public int findPainted(int x, int y){
        if(pe.size() > 0){
            for (int i = 0; i < pe.size(); i++){
                if (pe.get(i).onPainted(x, y)){
                    return i;
                }
            }
        }
        return -1;
    }

    public Colors getBackgroundColor(){ return backgroundColor; }

    public void setBackgroundColor(Colors backCol){ backgroundColor = backCol; }

    public int getWidth(){ return width; }

    public void setWidth(int w){ width = w; }

    public int getHeight(){ return height; }

    public void setHeight(int h){ height = h; }

    public ArrayList<PaintedElement> getPaintedElements(){ return pe; }

    public void addPaintedElement(PaintedElement element){ pe.add(element); }
}

