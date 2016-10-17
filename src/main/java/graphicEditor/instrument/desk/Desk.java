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

    private Controller controller;

    /**
     * Объект в FXML
     */
    private GraphicsContext graphicsContext;
    private Label coordsLabel;
    private Label sizeLabel;
    private Image coordsIcon =  new Image("/images/misc/coordsIcon.png");
    private Image sizeIcon =  new Image("/images/misc/sizeIcon.png");

    private Integer width;
    private Integer height;

    /**
     * Конструктор
     */
    public Desk (Controller controller){
        this.controller = controller;
        initialize();
    }

    /**
     * Инициализация - параметры объекта deskCanvas: ширина, высота, иконки координат и размера доски,
     * возврат координат и размеров на доску, установка цвета фона.
     */
    private void initialize(){
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
    }

    /**
     * Выполнение
     */
    private void run(){
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

        //Мышь двигается с нажатием
        deskCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                getCoords(event);
                activeInstrument.instrumentAction(event, graphicsContext);
            }
        });

        //Клик мыши
        deskCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                activeInstrument.instrumentAction(event, graphicsContext);
            }
        });

        //Нажатие мыши
        deskCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                lineWidth = Integer.parseInt(widthSetter.getText());
            }
        });
    }

    /**
     * Установить цвет фона и нарисовать его
     */
    private void setBackground(){
        graphicsContext.setFill(backgroundColor);
        graphicsContext.fillRect(0, 0, width, height);
    }

    /**
     * Координаты мыши на доске
     * @param event
     */
    private void getCoords(MouseEvent event){
        Integer x = (int) event.getX();
        Integer y = (int) event.getY();
        coordsLabel.setText(x.toString() + ", " + y.toString());
    }

}

