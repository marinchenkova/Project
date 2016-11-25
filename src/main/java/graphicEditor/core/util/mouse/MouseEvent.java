package graphicEditor.core.util.mouse;

/**
 * {@link MouseEvents} - класс, инкапсулирующий событие мыши.
 */
public class MouseEvent {
    private MouseEvents event;
    private int mButton;
    private int x;
    private int y;

    public MouseEvent(MouseEvents mouseEvent, int mouseButton, int mx, int my){
        event = mouseEvent;
        mButton = mouseButton;
        x = mx;
        y = my;
    }

    public MouseEvents getEvent(){ return event; }
    public int getMouseButton(){ return mButton; }
    public int getX(){ return x; }
    public int getY(){ return y; }
}
