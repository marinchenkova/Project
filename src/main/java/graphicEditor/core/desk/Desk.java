package graphicEditor.core.desk;

import static graphicEditor.core.util.MouseEvents.*;

/**
 * Доска
 */
public class Desk {
/*
    private GraphicsContext graphicsContext;
    private Label coordsLabel;
    private Label sizeLabel;
    private Image coordsIcon =  new Image("/images/misc/coordsIcon.png");
    private Image sizeIcon =  new Image("/images/misc/sizeIcon.png");

    private Integer width;
    private Integer height;
    private MouseEvent activeEvent;

*/
    public Desk (){

    }


    public void initialize(){
        /*
        width = (int) deskCanvas.getWidth();
        height = (int) deskCanvas.getHeight();

        ImageView coordsImage = controller.getCoordsImage();
        ImageView sizeImage = controller.getSizeImage();
        coordsImage.setImage(coordsIcon);
        sizeImage.setImage(sizeIcon);

        coordsLabel = controller.getCoordsLabel();
        sizeLabel = controller.getSizeLabel();
        sizeLabel.setText(width.toString() + ", " + height.toString());

        graphicsContext = deskCanvas.getGraphicsContext2D();
        setBackground();

        run();
        */
    }

    public void run(graphicEditor.core.util.MouseEvent me){
        //Мышь на доске
        if(me.getEvent() == ENTERED){
            setCoords(me);
        }

        //Мышь не на доске
        if(me.getEvent() == EXITED){
            //coordsLabel.setText("");
        }

        //Мышь двигается по доске
        if(me.getEvent() == MOVED){
            //activeEvent = event;
            //PaintedElement.findPainted(event);
            setCoords(me);
        }

        //Мышь двигается с нажатием
        if(me.getEvent() == DRAGGED){
            setCoords(me);
            //activeInstrument.instrumentAction(event, graphicsContext);
        }

        //Клик мыши
        if(me.getEvent() == DRAGGED){

        }

        //Нажатие мыши
        if(me.getEvent() == PRESSED){
            //lineWidth = Integer.parseInt(widthSetter.getText());
            //activeInstrument.instrumentAction(event, graphicsContext);
        }

        //Отжатие мыши
        if(me.getEvent() == RELEASED){
            //activeInstrument.instrumentAction(event, graphicsContext);
            //PaintedElement.findPainted(event);
        }
    }

    /**
     * Установить цвет фона и нарисовать его
     */
    public void setBackground(){

    }

    /**
     * Координаты мыши на доске
     */
    public void setCoords(graphicEditor.core.util.MouseEvent me){
        //coordsLabel.setText(me.getX() + ", " + me.getY());
    }

    /*
    protected void setDeskCursor(Canvas deskCanvas, Cursor cursorImage){
        deskCanvas.setCursor(cursorImage);
    }
    */
}

