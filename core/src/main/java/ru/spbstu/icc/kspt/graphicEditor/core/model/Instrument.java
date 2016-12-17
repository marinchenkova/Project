package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * {@link Instrument} - абстрактный класс для реализации инструментов рисования.
 */
public abstract class Instrument {

    protected Object icon;
    protected PaintedElement pe;

    protected Instrument(){}

    public Instrument(Object buttonIcon){
        icon = buttonIcon;
    }

    public Object getIcon() throws NullPointerException{ return icon; }

    public abstract void onPressed(Point point, double width);

    public abstract void onDragged(Point point);

    public void onReleased() throws NullPointerException{ pe.close(); }

    public PaintedElement getPE(){ return pe; }

    @Override
    public String toString(){ return this.getClass().toString(); }

}

