package ru.spbstu.icc.kspt.graphicEditor.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.*;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.spbstu.icc.kspt.graphicEditor.app.view.MainApp;
import ru.spbstu.icc.kspt.graphicEditor.core.Desk;
import ru.spbstu.icc.kspt.graphicEditor.core.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.instruments.*;
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
     * Инициализация
     */
    @FXML
    public void initialize() throws Exception {
        initInstruments();
        activeInstrument = brush;

        initDesk();

        initInfo();

        onButton();

        paint();
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
    }

    /**
     * Инициализация доски: размеры, цвет фона
     */
    private void initDesk(){
        deskGC = deskCanvas.getGraphicsContext2D();
        desk = new Desk((int) deskCanvas.getWidth(), (int) deskCanvas.getHeight(), Color.WHITE);

        deskGC.setFill((Color) desk.getBackgroundColor());
        deskGC.fillRect(0, 0, desk.getWidth(), desk.getHeight());
    }

    private void initInfo() {
        setLineWidth();

        coordsIcon.setImage(new Image("/icons/coordsIcon.png"));
        sizeIcon.setImage(new Image("/icons/sizeIcon.png"));

        instrumentImage.setImage((Image) activeInstrument.getIcon());
    }

    /**
     * Обработка нажатия кнопок
     */
    public void onButton(){
        agentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                activeInstrument = agent;
                instrumentImage.setImage((Image) activeInstrument.getIcon());
            }
        });

        brushButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                activeInstrument = brush;
                instrumentImage.setImage((Image) activeInstrument.getIcon());
            }
        });
    }

    /**
     * Обработка событий мыши
     */
    private void paint(){
        deskCanvas.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCoordsLabel(event);
            }
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCoordsLabel(null);
            }
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCoordsLabel(event);
            }
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onInstrumentAction(event);
            }
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                desk.addPaintedElement(activeInstrument.getPaintedElement());
            }
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onInstrumentAction(event);
                setCoordsLabel(event);
            }
        });
    }

    private void onInstrumentAction(MouseEvent event){
        if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
            activeInstrument.setDragged(true);
        } else activeInstrument.setDragged(false);

        activeInstrument.onAction(new Point((int) event.getX(), (int) event.getY()), lineWidth);
    }

    private void setLineWidth(){
        lineWidth = Integer.parseInt(widthSetter.getText());
    }

    private void setCoordsLabel(MouseEvent event){
        if(event != null){
            coordsLabel.setText((int) event.getX() + ", " + (int) event.getY());
        } else coordsLabel.setText("");
    }

    private void setButtonIcon(Button button, Image image){
        button.setPadding(new Insets(0, 0, 0, 0));
        button.setGraphic(new ImageView(image));
    }

    public void setApplication(MainApp mainApp){
        app = mainApp;
    }
}
