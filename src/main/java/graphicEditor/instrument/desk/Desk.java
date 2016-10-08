package graphicEditor.instrument.desk;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


/**
 * Доска
 */
public class Desk extends Instrument {
    /**
     * Поля
     */

    /**
     * Класс - контроллер
     */
    private Controller controller;

    /**
     * Объект в FXML
     */
    private GraphicsContext graphicsContext;
    private Label coordsLabel;
    private ImageView coordsImage;
    private Image coordsIcon =  new Image("/images/misc/coordsIcon.png");

    private double width;
    private double height;
    public Integer x;
    public Integer y;


    //Конструктор
    public Desk (){

    }

    public Desk (Controller controller){
        this.controller = controller;
        initialize();
    }

    public void initialize(){
        deskCanvas = controller.deskCanvas;

        width = deskCanvas.getWidth();
        height = deskCanvas.getHeight();

        coordsLabel = controller.coordsLabel;
        coordsImage = controller.coordsImage;
        coordsImage.setImage(coordsIcon);

        graphicsContext = deskCanvas.getGraphicsContext2D();
        setBackground();

        run();
    }

    //Выполнение
    public void run(){
        //Мышь на доске
        deskCanvas.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                getCoords(event);
            }
        });

        //Мышь не на доске
        deskCanvas.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                coordsLabel.setText("");
            }
        });

        //Мышь двигается на доске
        deskCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                getCoords(event);
            }
        });

        //Нажатие мыши
        deskCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                activeInstrument.instrumentAction(event, graphicsContext);
            }
        });

        //Мышь двигается с нажатием
        deskCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                getCoords(event);
                activeInstrument.instrumentAction(event, graphicsContext);
            }
        });
    }

    //Установить фон белым
    public void setBackground(){
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, width, height);
    }

    //Координаты мыши на доске
    public void getCoords(MouseEvent event){
        x = (int) event.getX();
        y = (int) event.getY();
        coordsLabel.setText(x.toString() + ", " + y.toString());
    }

}

