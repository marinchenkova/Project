package graphicEditor.core.instrument.instruments;

import graphicEditor.core.instrument.*;
import graphicEditor.core.paintedElements.PaintedElement;
import graphicEditor.core.paintedElements.elements.BrushElement;
import graphicEditor.core.util.*;

import java.util.ArrayList;

import static graphicEditor.core.util.MouseEvents.*;

/**
 * Кисть
 */
public class Brush extends Instrument {

    public Brush(){}
    public Brush(String butIcPath, String cursIcPath){ super(butIcPath, cursIcPath); }

    /**
     * Инициализация
     */
    public void initialize(){

    }

    /**
     * Применение кисти: при нажатии ЛКМ выполняется метод {@link BrushElement#paint}, ПКМ - выделение объекта
     * {@link Brush#elementAction}.
     * @param
     */
    @Override
    public void instrumentAction(MouseEvent me){
        MouseEvents event = me.getEvent();
        int mButton = me.getMouseButton();
        ArrayList<PaintedElement> pe = me.getPaintedEl();
        int x = me.getX();
        int y = me.getY();
        int width = me.getWidth();

        if(mButton == 1){
            if(event == PRESSED){
                pe.add(new BrushElement(me));
            }
            if(event == DRAGGED){
                pe.get(pe.size()-1).paint(me);
            }
            if(event == RELEASED){
                isOnPainted = true;
            }
        }

        if(mButton == 2 && isOnPainted) {
            if (event == PRESSED) {
                elementAction(me);
            }
        }
    }

    /**
     * Действие с объектом {@link BrushElement}
     */
    public void elementAction(MouseEvent me){
        System.err.println("TEST: " + this.getClass() + ".elementAction() : " + me.getX() + "," + me.getY());
        //PaintedElement.findPainted(me);
        //me.getPaintedEl().get(PaintedElement.getElementNumber()).outline();
    }
}
