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
    public PaintedElement onAction(MouseEvent me, int lineWidth){
        MouseEvents event = me.getEvent();
        int mButton = me.getMouseButton();
        int x = me.getX();
        int y = me.getY();
        BrushElement be = new BrushElement(x, y, lineWidth);

        if(mButton == 1){
            switch (event){
                case PRESSED:
                    return be;
                case DRAGGED:
                    be.paintAtom(x, y);
                    break;
                case RELEASED:
                    return be;
            }
        }
        return be;
    }
}
