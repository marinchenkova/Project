package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * {@link Instrument} - абстрактный класс для реализации инструментов рисования.
 */
public abstract class Instrument {

    protected Object icon;
    protected Object cursor;
    protected PaintedElement pe;

    protected Instrument(){}

    public Instrument(Object buttonIcon, Object cursorIcon){
        icon = buttonIcon;
        cursor = cursorIcon;
    }

    public Object getIcon() throws NullPointerException{ return icon; }

    public Object getCursor() throws NullPointerException{ return cursor; }

    public abstract void onPressed(Point point, double width);

    public abstract void onDragged(Point point);

    public PaintedElement onReleased() throws NullPointerException{
        pe.close();
        return pe;
    }

    @Override
    public String toString(){ return this.getClass().toString(); }

}

