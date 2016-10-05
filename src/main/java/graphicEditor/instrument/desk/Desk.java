package graphicEditor.instrument.desk;

import graphicEditor.Controller;
import graphicEditor.MainApp;
import graphicEditor.instrument.Instrument;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Canvas deskCanvas;
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

    public  void initialize(){
        deskCanvas = controller.desk;

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
        deskCanvas.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                getCoords(event);
            }
        });
        deskCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                getCoords(event);
            }
        });
        deskCanvas.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                coordsLabel.setText("");
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
        coordsLabel.setText(x.toString() + ',' + y.toString());
    }
}

