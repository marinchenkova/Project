package graphicEditor.core.instrument;

import graphicEditor.core.paintedElements.PaintedElement;
import graphicEditor.core.util.mouse.MouseEvent;

import java.util.ArrayList;


/**
 * {@link Instrument} - абстрактный класс для реализации инструментов рисования.
 */
public abstract class Instrument {


    protected final String buttonIconPath;
    protected final String cursorIconPath;

    protected boolean isOnPainted;


    public Instrument(){
        buttonIconPath = null;
        cursorIconPath = null;
    }

    public Instrument(String butIcPath, String cursIcPath){
        buttonIconPath = butIcPath;
        cursorIconPath = cursIcPath;
    }

    public abstract void initialize();

    public Instrument getInstrument(){ return this; }

    /**
     *
     */
    public PaintedElement onAction(MouseEvent me, int lineWidth) throws NullPointerException {
        return null;
    }

    @Override
    public String toString(){ return this.getClass().toString(); }


    public String getButtonIconPath() throws NullPointerException{
        return buttonIconPath;
    }

    public String getCursorIconPath() throws NullPointerException{
        return cursorIconPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrument)) return false;

        Instrument that = (Instrument) o;

        if (isOnPainted != that.isOnPainted) return false;
        if (getButtonIconPath() != null ? !getButtonIconPath().equals(that.getButtonIconPath()) :
                that.getButtonIconPath() != null)
            return false;
        return getCursorIconPath() != null ? getCursorIconPath().equals(that.getCursorIconPath()) :
                that.getCursorIconPath() == null;
    }

    @Override
    public int hashCode() {
        int result = getButtonIconPath() != null ? getButtonIconPath().hashCode() : 0;
        result = 31 * result + (getCursorIconPath() != null ? getCursorIconPath().hashCode() : 0);
        result = 31 * result + (isOnPainted ? 1 : 0);
        return result;
    }
}

