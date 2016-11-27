package ru.spbstu.icc.kspt.graphicEditor.core.instruments;

import ru.spbstu.icc.kspt.graphicEditor.core.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.mouse.MouseEvent;
import ru.spbstu.icc.kspt.graphicEditor.core.util.mouse.MouseEvents;

/**
 * Кисть
 */
public class Brush extends Instrument {

    private BrushElement be;

    public Brush(){}
    public Brush(String butIcPath, String cursIcPath){ super(butIcPath, cursIcPath); }

    /**
     * Применение кисти
     */
    @Override
    public void onAction(MouseEvent me, int lineWidth){
        MouseEvents event = me.getEvent();
        int x = me.getX();
        int y = me.getY();
        be = new BrushElement(x, y, lineWidth);

        if(event == MouseEvents.DRAGGED){
            be.paintAtom(x, y);
        }
    }

    public BrushElement getBrushElement(){ return be; }
}
