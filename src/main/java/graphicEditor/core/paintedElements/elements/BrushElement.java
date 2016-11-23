package graphicEditor.core.paintedElements.elements;

import graphicEditor.core.paintedElements.PaintedElement;
import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента кисть
 */
public class BrushElement extends PaintedElement{

    private int lineWidth;

    private ArrayList<Integer> xList = new ArrayList<Integer>();
    private ArrayList<Integer> yList = new ArrayList<Integer>();

    public BrushElement(int liWid, int x, int y){
        lineWidth = liWid;
        paint(x, y);
    }

    /**
     * Нарисовать круг заданной ширины
     */
    @Override
    public void paint(int x, int y){
        /*graphicsContext.fillOval((int) (event.getX() - width/2 + 1),(int) (event.getY() - width/2 + 1), width, width);*/
        xList.add(x);
        yList.add(y);
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
    public boolean onPaintedElement(int mx, int my){
        for (int i = 0; i < xList.size(); i++) {
            if ((Math.abs(mx - lineWidth / 2 - xList.get(i)) <= lineWidth / 2) &&
                       (Math.abs(my - lineWidth / 2 - yList.get(i)) <= lineWidth / 2)){
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
