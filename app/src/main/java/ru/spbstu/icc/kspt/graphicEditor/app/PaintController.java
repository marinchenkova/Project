package ru.spbstu.icc.kspt.graphicEditor.app;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.spbstu.icc.kspt.graphicEditor.app.view.MainApp;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Cache;
import ru.spbstu.icc.kspt.graphicEditor.core.model.Desk;
import ru.spbstu.icc.kspt.graphicEditor.core.model.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.model.instruments.*;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Класс {@link PaintController} реализует взаимодействие объектов рисования: доски и инструментов
 */
public class PaintController {

    private MainApp app;

    private Instrument activeInstrument;
    private int lineWidth;

    private Instrument agent;
    private Instrument brush;

    private Desk desk;
    private Cache cache;

    //TODO список: карта курсоров
    private Cursor agentCursor;
    private Cursor brushCursor;

    @FXML
    private Canvas deskCanvas = new Canvas();
    private GraphicsContext deskGC;

    @FXML
    private ImageView instrumentImage = new ImageView();
    @FXML
    private TextField widthSetter = new TextField();

    @FXML
    private Button agentButton = new Button();
    @FXML
    private Button brushButton = new Button();
    @FXML
    private Button lineButton = new Button();
    @FXML
    private Button rectangleButton = new Button();

    @FXML
    private Label coordsLabel = new Label();
    @FXML
    private Label sizeLabel = new Label();
    @FXML
    private ImageView coordsIcon = new ImageView();
    @FXML
    private ImageView sizeIcon = new ImageView();

    public PaintController() {}

    /**
     * Инициализация средств рисования
     */
    @FXML
    public void initialize() throws Exception {
        initInstruments();
        initDesk();
        initInfo();
    }

    /**
     * Инициализация инструментов: иконки кнопок, курсоров
     */
    private void initInstruments(){
        try{
            //Агент
            Image agentIcon = new Image("/icons/agentIcon.png");
            ImageCursor agentCursor = new ImageCursor(new Image("/cursors/agentCursor.png"),
                    2, 2);
            agent = new Agent(agentIcon, agentCursor);
            setButtonIcon(agentButton, agentIcon);

            //Кисть
            Image brushIcon = new Image("/icons/brushIcon.png");
            ImageCursor brushCursor = new ImageCursor(new Image("/cursors/brushCursor.png"),
                    15, 15);
            brush = new Brush(brushIcon, brushCursor);
            setButtonIcon(brushButton, brushIcon);

            //TODO
            //Линия


            //Прямоугольник
        } catch (IllegalArgumentException e){
            System.err.println(this + ": " + e);
        }

        //Инструмент по умолчанию
        activeInstrument = brush;
    }

    /**
     * Инициализация доски: размеры, цвет фона
     */
    public void initDesk(){
        deskGC = deskCanvas.getGraphicsContext2D();
        desk = new Desk((int) deskCanvas.getWidth(), (int) deskCanvas.getHeight(), Color.WHITE);

        deskGC.setFill((Color) desk.getBackgroundColor());
        deskGC.fillRect(0, 0, desk.getWidth(), desk.getHeight());
    }

    public void initInfo() {
        lineWidth = Integer.parseInt(widthSetter.getText());
        coordsLabel.setText(desk.getSizeString());

        coordsIcon.setImage(new Image("/icons/coordsIcon.png"));
        sizeIcon.setImage(new Image("/icons/sizeIcon.png"));

        instrumentImage.setImage((Image) activeInstrument.getIcon());
    }

    /**
     * Обработка нажатия кнопок
     */
    public void onButton(){
        agentButton.setOnAction(event -> {
            activeInstrument = agent;
            instrumentImage.setImage((Image) activeInstrument.getIcon());
        });

        brushButton.setOnAction(event -> {
            activeInstrument = brush;
            instrumentImage.setImage((Image) activeInstrument.getIcon());
        });
    }

    /**
     * Обработка событий мыши
     */
    public void paint(){
        deskCanvas.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> setCoordsLabelText(event));

        deskCanvas.addEventHandler(MouseEvent.MOUSE_EXITED, event -> setCoordsLabelText(null));

        deskCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> setCoordsLabelText(event));

        deskCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            activeInstrument.mousePressed(new Point((int) event.getX(), (int) event.getY()), lineWidth);
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            desk.addPaintedElement(activeInstrument.mouseReleased());
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            activeInstrument.mouseDragged(new Point((int) event.getX(), (int) event.getY()));
            setCoordsLabelText(event);
        });
    }

    public void setLineWidth(Integer width){
        widthSetter.setText(width.toString());
    }

    public void setSize(int w, int h){
        desk.setSize(w, h);

        deskCanvas.setWidth(w);
        deskCanvas.setHeight(h);

        sizeLabel.setText(desk.getSizeString());
    }

    public void setCoordsLabelText(MouseEvent event){
        if(event != null){
            coordsLabel.setText((int) event.getX() + ", " + (int) event.getY());
        } else coordsLabel.setText("");
    }

    public void setButtonIcon(Button button, Image image){
        button.setPadding(new Insets(0, 0, 0, 0));
        button.setGraphic(new ImageView(image));
    }

    public void setApplication(MainApp mainApp){
        app = mainApp;
    }
}
