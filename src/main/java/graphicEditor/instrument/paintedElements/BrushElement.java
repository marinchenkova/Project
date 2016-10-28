package graphicEditor.instrument.paintedElements;

import graphicEditor.instrument.mainInstruments.Brush;
import graphicEditor.instrument.Instrument;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Объект, нарисованный одним действием инструмента: кисть, заливка, фигура
 */
public class BrushElement extends Brush{

    private Color color = activeColor;
    private Integer width = (int) lineWidth;

    private ArrayList<Integer> xList = new ArrayList<Integer>();
    private ArrayList<Integer> yList = new ArrayList<Integer>();

    public BrushElement(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(color);
        paint(event, graphicsContext);
    }

    /**
     * Нарисовать овал заданной ширины {@link Instrument#lineWidth} и цвета {@link Instrument#activeColor}
     */
    public void paint(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.fillOval((int) (event.getX() - width/2 + 1),(int) (event.getY() - width/2 + 1), width, width);
        xList.add((int) (event.getX() - width/2 + 1));
        yList.add((int) (event.getY() - width/2 + 1));
    }

    /**
     * Нарисовать весь элемент. Метод используется при удалении одного из элементов.
     */
    public void paintWhole(GraphicsContext graphicsContext){
        graphicsContext.setFill(color);
        for (int i = 0; i < xList.size(); i++) {
            graphicsContext.fillOval(xList.get(i),yList.get(i), width, width);
        }
    }

    /**
     * Если курсор находится на объекте, возвращает true
     */
    public boolean onBrushElement(int mx, int my){
        for (int i = 0; i < xList.size(); i++) {
            if ((Math.abs(mx - xList.get(i)) <= width) &&
                    (Math.abs(my - yList.get(i)) <= width)){
                        return true;
            }
        }
        return false;
    }

    /**
     * Удаление графического элемента: элемнт удаляется из списка нарисованных элементов данного типа,
     * а затем все оставшиеся элементы отрисовываются заново.
     */
    public void delete(GraphicsContext graphicsContext) {
        desk.setBackground();
        brushElements.remove(elementNumber);
        if(brushElements.size() > 0 ){
            for (BrushElement brushElement : brushElements) {
                brushElement.paintWhole(graphicsContext);
            }
        }
    }
}
