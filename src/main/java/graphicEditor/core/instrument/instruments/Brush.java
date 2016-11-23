package graphicEditor.core.instrument.instruments;

import graphicEditor.core.instrument.*;
import graphicEditor.core.paintedElements.PaintedElement;
import graphicEditor.core.paintedElements.elements.BrushElement;

/**
 * Кисть
 */
public class Brush extends Instrument {


    public Brush(String butIcPath, String cursIcPath){ super(butIcPath, cursIcPath); }

    /**
     * Инициализация
     */
    public void initialize(){

    }

    /**
     * Применение кисти: при нажатии ЛКМ выполняется метод {@link BrushElement#paint}, ПКМ - выделение объекта
     * {@link Brush#elementAction}.
     * @param event событие мыши
     */
    @Override
    public void instrumentAction(int mouseButton, String event, int x, int y){
        if(mouseButton == 1){
            if(event == "PRESSED"){
                paintedElements.add(new BrushElement(x, y));
            }
            if(event == "DRAGGED"){
                paintedElements.get(paintedElements.size()-1).paint(x, y);
            }
            if(event == "RELEASED"){
                isOnPainted = true;
            }
        }

        if(mouseButton == 2 && isOnPainted) {
            if (event == "PRESSED") {
                elementAction(x, y);
            }
        }
    }

    /**
     * Действие с объектом {@link BrushElement}
     */
    public void elementAction(int x, int y){
        PaintedElement.findPainted(x, y);
        paintedElements.get(PaintedElement.getElementNumber()).outline();
    }
}
