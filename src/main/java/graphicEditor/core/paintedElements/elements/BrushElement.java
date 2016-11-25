package graphicEditor.core.paintedElements.elements;

import graphicEditor.core.paintedElements.PaintedElement;
import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента кисть
 */
public class BrushElement implements PaintedElement{

    private int lineWidth;
    private ArrayList<Integer> xList = new ArrayList<Integer>();
    private ArrayList<Integer> yList = new ArrayList<Integer>();

    public BrushElement(int x, int y, int width){
        lineWidth = width;
        paintAtom(x, y);
    }

    /**
     * Нарисовать круг заданной ширины
     */
    public void paintAtom(int x, int y){
        /*graphicsContext.fillOval((int) (event.getX() - width/2 + 1),(int) (event.getY() - width/2 + 1), width, width);*/
        xList.add(x);
        yList.add(y);
    }

    /**
     * Возвращение списков координат: используеся при отрисовке этого элемента и при удалении одного из элементов
     * {@link PaintedElement}.
     */
    public ArrayList<Integer> getXList(){
        return xList;
    }

    public ArrayList<Integer> getYList(){
        return yList;
    }

    /**
     * Если курсор находится на объекте, возвращает true
     */
    public boolean onPainted(int x, int y){
        for (int i = 0; i < xList.size(); i++) {
            if ((Math.abs(x - lineWidth / 2 - xList.get(i)) <= lineWidth / 2) &&
                       (Math.abs(y - lineWidth / 2 - yList.get(i)) <= lineWidth / 2)){
                return true;
            }
        }
        return false;
    }

    /**
     * Удаление графического элемента: элемент удаляется из списка нарисованных элементов данного типа,
     * а затем все оставшиеся элементы отрисовываются заново.
     */

    public void delete(ArrayList<PaintedElement> pe, int i) {
        pe.remove(i);
        for(PaintedElement e : pe){
            pe.remove(e);
        }
    }


    public void outline(){

    }

}
