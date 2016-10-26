package graphicEditor.instrument.paintedElements;

import graphicEditor.instrument.mainInstruments.Brush;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Объект, нарисованный одним действием инструмента: кисть, заливка, фигура
 */
public class BrushElement extends Brush{

    private Color color = activeColor;
    private Integer x;
    private Integer y;
    private Integer width = (int) lineWidth;

    private List<Integer> xList;
    private List<Integer> yList;

    public BrushElement(MouseEvent event, GraphicsContext graphicsContext){
        initialize();
        paint(event, graphicsContext);
    }

    private void initialize(){
        xList = new ArrayList<Integer>();
        yList = new ArrayList<Integer>();
    }

    public void paint(MouseEvent event, GraphicsContext graphicsContext){
        graphicsContext.setFill(activeColor);
        graphicsContext.fillOval(event.getX() - width/2 + 1, event.getY() - width/2 + 1, width, width);
        x = (int) (event.getX() - width/2 + 1);
        y = (int) (event.getY() - width/2 + 1);
        xList.add(x);
        yList.add(y);
    }

    /**
     * Если курсор находится на объекте, возвращает true
     */
    public boolean isBrushElement(Integer mx, Integer my){
        for (int i = 0; i < xList.size(); i++) {
            if ((mx - x) < x + width){
                for (int j = 0; j < yList.size(); j++) {
                    if ((my - y) < y + width) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
