package graphicEditor.core.instrument.instruments;

import graphicEditor.core.instrument.*;
import graphicEditor.core.paintedElements.PaintedElement;
import graphicEditor.core.paintedElements.elements.BrushElement;
import graphicEditor.core.util.mouse.*;

import java.util.ArrayList;

import static graphicEditor.core.util.mouse.MouseEvents.*;

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
     * Применение кисти
     */
    @Override
    public void instrumentAction(MouseEvent me, int lineWidth, ArrayList<PaintedElement> pe){
        MouseEvents event = me.getEvent();
        int mButton = me.getMouseButton();
        int x = me.getX();
        int y = me.getY();
        int w = lineWidth;

        if(mButton == 1){
            if(event == PRESSED){
                pe.add(new BrushElement(x, y, w));
            }
            if(event == DRAGGED){
                pe.get(pe.size()-1).paintAtom(x, y);
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
    private void elementAction(MouseEvent me){
        System.err.println("TEST: " + this.getClass() + ".elementAction() : " + me.getX() + "," + me.getY());
        //PaintedElement.findPainted(me);
        //me.getPaintedEl().get(PaintedElement.getElementNumber()).outline();
    }
}
