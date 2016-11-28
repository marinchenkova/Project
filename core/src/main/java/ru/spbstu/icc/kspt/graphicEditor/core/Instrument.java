package ru.spbstu.icc.kspt.graphicEditor.core;

import ru.spbstu.icc.kspt.graphicEditor.core.util.mouse.MouseEvent;


/**
 * {@link Instrument} - абстрактный класс для реализации инструментов рисования.
 */
public abstract class Instrument {

    protected boolean isOnPainted;


    public Instrument(){

    }

    public Instrument getInstrument(){ return this; }

    /**
     *
     */
    public void onAction(MouseEvent me, int lineWidth) throws NullPointerException{}

    @Override
    public String toString(){ return this.getClass().toString(); }

}

