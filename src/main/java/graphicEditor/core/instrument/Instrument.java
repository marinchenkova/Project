package graphicEditor.core.instrument;

import graphicEditor.core.paintedElements.PaintedElement;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Instrument} - абстрактный класс для реализации инструментов рисования.
 */
public abstract class Instrument {
    /**
     * Активные значения - общие переменные для классов-наследников, которые изменяются при работе программы
     */
    /*protected static Instrument activeInstrument;
    protected static Color activeColor;
    protected static Color backgroundColor;
    protected static Integer lineWidth = 10;
    protected static TextField widthSetter;
    protected static Canvas deskCanvas;
    protected static Desk desk;
    */

    protected String buttonIconPath;
    protected String cursorIconPath;

    protected List<PaintedElement> paintedElements = new ArrayList<PaintedElement>();
    protected boolean isOnPainted;
/*
    protected static boolean isOnPainted = false;
    protected static int elementNumber = 0;
    */

    protected Instrument(){}

    public Instrument(String butIcPath, String cursIcPath){
        buttonIconPath = butIcPath;
        cursorIconPath = cursIcPath;
    }

    public abstract void initialize();

    public Instrument getInstrument(){ return this; }

    /**
     * @param event событие мыши
     */
    public abstract void instrumentAction(int mouseButton, String event, int x, int y);

    @Override
    public String toString(){
        return this.getClass().toString();
    }

    //TODO хэш-код
    @Override
    public int hashCode(){
        return 0;
    }

    public String getButtonIconPath(){
        return buttonIconPath;
    }

    public String getCursorIconPath(){
        return cursorIconPath;
    }

    /*
     * Установка иконки активного инструмента в панель настроек

    protected void setActiveInstrumentIcon(){
        instrumentImage.setImage(instrumentIcon);
    }
    */

    /*
    protected void setDeskCursor(Canvas deskCanvas, Cursor cursorImage){
        deskCanvas.setCursor(cursorImage);
    }
    */
}

