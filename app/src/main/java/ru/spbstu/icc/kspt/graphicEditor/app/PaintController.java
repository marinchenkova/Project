package ru.spbstu.icc.kspt.graphicEditor.app;

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
import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
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

    private Color activeColor = Color.BLACK;
    private Instrument activeInstrument;
    private double activeWidth;

    private PaintedElement elementToEdit;
    private boolean editing = false;
    private Point refPoint = new Point(0, 0);


    private Agent agent;
    private Instrument brush;

    private Desk desk;
    private Cache cache = new Cache();

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
        repaintBackground();
    }

    public void initInfo() {
        activeWidth = Integer.parseInt(widthSetter.getText());
        coordsLabel.setText(desk.getSizeString());
        sizeLabel.setText(desk.getSizeString());

        coordsIcon.setImage(new Image("/icons/coordsIcon.png"));
        sizeIcon.setImage(new Image("/icons/sizeIcon.png"));

        instrumentImage.setImage((Image) activeInstrument.getIcon());
    }

    /**
     * Обработка нажатия кнопок и изменения ширины линии
     */
    public void onSettingsChanged(){
        agentButton.setOnAction(event -> {
            editing = true;
            instrumentImage.setImage((Image) agent.getIcon());
        });

        brushButton.setOnAction(event -> {
            activeInstrument = brush;
            editing = false;
            instrumentImage.setImage((Image) activeInstrument.getIcon());
        });

        widthSetter.setOnAction(event -> {
            getActiveWidth();
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
            getActiveWidth();
            onMousePressed(event);
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            onMouseReleased();
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            onMouseDragged(event);
            setCoordsLabelText(event);
        });
    }

    private void onMousePressed(MouseEvent event){
        refPoint = new Point(event.getX() , event.getY());

        if(editing){
            elementToEdit = desk.findPainted(refPoint);

        } else {
            paintAtom(refPoint, activeWidth);
            activeInstrument.onPressed(refPoint, activeWidth);
        }
    }

    private void onMouseDragged(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

        //В режиме редактирования
        if(elementToEdit != null && editing) {
            //Левая кнопка мыши: перемещение
            if (event.getButton() == MouseButton.PRIMARY) {
                elementToEdit.translate(x - refPoint.getX(), y - refPoint.getY());
                repaint();
                refPoint = new Point(x ,y);
            }

            //Средняя кнопка мыши: вращение
            else if (event.getButton() == MouseButton.MIDDLE) {
                elementToEdit.rotate(0.031415 * (refPoint.getX() - x));
                repaint();
                refPoint = new Point(x ,y);
            }

            //Правая кнопка мыши: масштабирование
            else if (event.getButton() == MouseButton.SECONDARY) {
                double kx = (x - elementToEdit.getCenter().getX()) /
                            (refPoint.getX() - elementToEdit.getCenter().getX());
                double ky = (y - elementToEdit.getCenter().getY()) /
                            (refPoint.getY() - elementToEdit.getCenter().getY());
                elementToEdit.scale(kx, ky);
                repaint();
                refPoint = new Point(x ,y);
            }

        //В режиме рисования
        } else if(!editing) {
            paintAtom(new Point(x ,y), activeWidth);
            activeInstrument.onDragged(new Point(x, y));
        }
    }

    private void onMouseReleased(){
        if(editing && elementToEdit != null){
            desk.addElement(elementToEdit);
            elementToEdit = null;

        } else if(!editing){
            desk.addElement(activeInstrument.onReleased());
        }
    }

    private void onMouseMoved(MouseEvent event){

    }

    private void repaint(){
        repaintBackground();

        if(desk.getPE().size() > 0){
            for(PaintedElement e : desk.getPE()){
                for(Point p : e.getPoints()) paintAtom(p, e.getWidth());
            }
        }

        for(Point p : elementToEdit.getPoints()){
            paintAtom(p, elementToEdit.getWidth());
        }
    }

    private void paintAtom(Point point, double width){
        deskGC.setFill(activeColor);
        deskGC.fillOval(point.getX(), point.getY(), width, width);
    }

    public double getActiveWidth(){
        activeWidth = Integer.parseInt(widthSetter.getText());
        return activeWidth;
    }

    private void repaintBackground(){
        deskGC.setFill((Color) desk.getBackgroundColor());
        deskGC.fillRect(0, 0, desk.getWidth(), desk.getHeight());
    }

    public void setActiveWidth(Integer width){
        widthSetter.setText(width.toString());
    }

    public void setSize(int w, int h){
        desk.setSize(w, h);

        deskCanvas.setWidth(w);
        deskCanvas.setHeight(h);

        sizeLabel.setText(desk.getSizeString());
    }

    private void setCoordsLabelText(MouseEvent event){
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
