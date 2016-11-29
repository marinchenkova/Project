package ru.spbstu.icc.kspt.graphicEditor.core;


import ru.spbstu.icc.kspt.graphicEditor.core.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * {@link Instrument} - абстрактный класс для реализации инструментов рисования.
 */
public abstract class Instrument {

    protected Object icon;
    protected Object cursor;
    protected boolean isDragged;
    protected PaintedElement pe;

    public Instrument(){

    }

    public Instrument(Object buttonIcon, Object cursorIcon){
        icon = buttonIcon;
        cursor = cursorIcon;
    }

    public void setDragged(boolean flag){ isDragged = flag; }

    public Instrument getInstrument(){ return this; }

    public PaintedElement getPaintedElement(){ return pe; }

    public Object getIcon() throws NullPointerException{ return icon; }

    public Object getCursor() throws NullPointerException{ return cursor; }

    public void onAction(Point point, int diameter) {}

    @Override
    public String toString(){ return this.getClass().toString(); }

}

