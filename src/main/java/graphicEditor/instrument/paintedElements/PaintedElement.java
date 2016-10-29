package graphicEditor.instrument.paintedElements;

import graphicEditor.instrument.Instrument;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Интерфейс для объектов, нарисованных одним действием инструмента: кисть, заливка, фигура
 */
public class PaintedElement extends  Instrument{

    public PaintedElement(){}

    /**
     * Поиск номера нарисованного элемента {@link PaintedElement} под курсором. Если элемент найден,
     * переменная {@link Instrument#isOnPainted} принимает значение {@code true}.
     */

    public static void findPainted(MouseEvent event){
        if(paintedElements.size() > 0){
            for(int i = 0; i < paintedElements.size(); i++) {
                if(paintedElements.get(i).onPaintedElement((int) event.getX(),(int) event.getY())){
                    isOnPainted = true;
                    elementNumber = i;
                    break;
                } else {
                    isOnPainted = false;
                }
            }
        } else {
            isOnPainted = false;
        }
        //System.err.print(elementNumber);System.err.print(' ');System.err.println(isOnPainted);
    }

    /**
     * Нарисовать объект с заданной шириной линии {@link Instrument#lineWidth} и цветом {@link Instrument#activeColor}
     */
    public void paint(MouseEvent event, GraphicsContext graphicsContext){}

    /**
     * Нарисовать весь элемент. Метод используется при удалении одного из элементов.
     */
    public void paintWhole(GraphicsContext graphicsContext){}

    /**
     * Если курсор находится на объекте, возвращает true
     */
    public boolean onPaintedElement(int mx, int my){return false;}

    /**
     * Удаление графического элемента: элемент удаляется из списка нарисованных элементов данного типа,
     * а затем все оставшиеся элементы отрисовываются заново.
     */
    public void delete(GraphicsContext graphicsContext){}
}
