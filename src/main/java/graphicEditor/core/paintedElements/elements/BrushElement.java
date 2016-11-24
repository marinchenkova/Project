package graphicEditor.core.paintedElements.elements;

import graphicEditor.core.paintedElements.PaintedElement;
import graphicEditor.core.util.MouseEvent;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента кисть
 */
public class BrushElement extends PaintedElement{

    private int lineWidth;

    private ArrayList<Integer> xList = new ArrayList<Integer>();
    private ArrayList<Integer> yList = new ArrayList<Integer>();

    public BrushElement(MouseEvent me){
        lineWidth = me.getWidth();
        paint(me);
    }

    /**
     * Нарисовать круг заданной ширины
     */
    @Override
    public void paint(MouseEvent me){
        /*graphicsContext.fillOval((int) (event.getX() - width/2 + 1),(int) (event.getY() - width/2 + 1), width, width);*/
        xList.add(me.getX());
        yList.add(me.getY());
    }

    /**
     * Отрисовка всего элемента. Метод используется при удалении одного из элементов {@link PaintedElement}.
     */
    @Override
    public void paintWhole(){
        for (int i = 0; i < xList.size(); i++) {
            //TODO
        }
    }

    /**
     * Если курсор находится на объекте, возвращает true
     */
    @Override
    public boolean onPaintedElement(MouseEvent me){
        for (int i = 0; i < xList.size(); i++) {
            if ((Math.abs(me.getX() - lineWidth / 2 - xList.get(i)) <= lineWidth / 2) &&
                       (Math.abs(me.getY() - lineWidth / 2 - yList.get(i)) <= lineWidth / 2)){
                return true;
            }
        }
        return false;
    }

    /**
     * Удаление графического элемента: элемент удаляется из списка нарисованных элементов данного типа,
     * а затем все оставшиеся элементы отрисовываются заново.
     */
    /*
    @Override
    public void delete(GraphicsContext graphicsContext) {
        desk.setBackground();
        paintedElements.remove(elementNumber);
        if(paintedElements.size() > 0 ){
            for (PaintedElement paintedElement : paintedElements) {
                paintedElement.paintWhole(graphicsContext);
            }
        }
    }

    @Override
    public void outline(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.rgb(0, 100, 100, 0.5));
        for (int i = 0; i < xList.size(); i++) {
            graphicsContext.fillOval(xList.get(i), yList.get(i), width, width);
        }
    }
    */
}
