package graphicEditor.core.util;

import graphicEditor.core.paintedElements.PaintedElement;

import java.util.ArrayList;

public class MouseEvent {
    private MouseEvents event;
    private int mButton;
    private ArrayList<PaintedElement> pe;
    private int x;
    private int y;
    private int width;

    public MouseEvent(MouseEvents mouseEvent, int mouseButton, ArrayList<PaintedElement> paintedEl,
                      int mx, int my, int lineWidth){
        event = mouseEvent;
        mButton = mouseButton;
        pe = paintedEl;
        x = mx;
        y = my;
        width = lineWidth;
    }

    public MouseEvents getEvent(){ return event; }
    public int getMouseButton(){ return mButton; }
    public ArrayList<PaintedElement> getPaintedEl(){ return pe; }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getWidth(){ return width; }
}
