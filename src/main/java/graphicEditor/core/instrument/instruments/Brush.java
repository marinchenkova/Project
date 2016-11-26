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
    public PaintedElement instrumentAction(MouseEvent me, int lineWidth){
        MouseEvents event = me.getEvent();
        int mButton = me.getMouseButton();
        int x = me.getX();
        int y = me.getY();
        int w = lineWidth;
        BrushElement be = new BrushElement(x, y, w);

        if(mButton == 1){
            if(event == PRESSED){
                return be;
            }
            if(event == DRAGGED){
                be.paintAtom(x, y);
            }
            if(event == RELEASED){
                isOnPainted = true;
                return be;
            }
        }

        if(mButton == 2 && isOnPainted) {
            if (event == PRESSED) {
                elementAction(me);
            }
        }
        return be;
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
