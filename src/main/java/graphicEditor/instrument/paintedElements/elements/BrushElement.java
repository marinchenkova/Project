package graphicEditor.instrument.paintedElements.elements;

import graphicEditor.instrument.Instrument;
import graphicEditor.instrument.paintedElements.PaintedElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента: кисть, заливка, фигура
 */
public class BrushElement extends PaintedElement{

    private Color color = activeColor;
    private Integer width = (int) lineWidth;

    private ArrayList<Integer> xList = new ArrayList<Integer>();
    private ArrayList<Integer> yList = new ArrayList<Integer>();

    public BrushElement(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(color);
        paint(event, graphicsContext);
    }

    /**
     * Нарисовать круг заданной ширины {@link Instrument#lineWidth} и цвета {@link Instrument#activeColor}
     */
    @Override
    public void paint(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.fillOval((int) (event.getX() - width/2 + 1),(int) (event.getY() - width/2 + 1), width, width);
        xList.add((int) (event.getX() - width/2 + 1));
        yList.add((int) (event.getY() - width/2 + 1));
    }

    /**
     * Отрисовка всего элемента. Метод используется при удалении одного из элементов {@link PaintedElement}.
     */
    @Override
    public void paintWhole(GraphicsContext graphicsContext){
        graphicsContext.setFill(color);
        for (int i = 0; i < xList.size(); i++) {
            graphicsContext.fillOval(xList.get(i),yList.get(i), width, width);
        }
    }

    /**
     * Если курсор находится на объекте, возвращает true
     */
    @Override
    public boolean onPaintedElement(int mx, int my){
        for (int i = 0; i < xList.size(); i++) {
            if ((Math.abs(mx - width / 2 - xList.get(i)) <= width / 2) &&
                    (Math.abs(my - width / 2 - yList.get(i)) <= width / 2)){
                return true;
            }
        }
        return false;
    }

    /**
     * Удаление графического элемента: элемент удаляется из списка нарисованных элементов данного типа,
     * а затем все оставшиеся элементы отрисовываются заново.
     */
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
}
