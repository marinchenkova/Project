package graphicEditor.core.instrument.instruments;

import graphicEditor.core.instrument.Instrument;
import graphicEditor.core.paintedElements.PaintedElement;

/**
 * Инструмент для выполнения действий над объектами {@link PaintedElement}
 */
public class Agent extends Instrument {

    public Agent(){}
    public Agent(String butIcPath, String cursIcPath){ super(butIcPath, cursIcPath); }

    public void initialize(){

    }

    public PaintedElement getScaled(PaintedElement element, int k){
        element.scale(k);
        return element;
    }

    public PaintedElement getTranslated(PaintedElement element, int dx, int dy){
        element.translate(dx, dy);
        return element;
    }

    public PaintedElement getRotated(PaintedElement element, double a){
        element.rotate(a);
        return element;
    }
}
