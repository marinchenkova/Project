package ru.spbstu.icc.kspt.graphicEditor.app.control;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.*;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.spbstu.icc.kspt.graphicEditor.app.view.MainApp;
import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.BrushElement;
import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.LineElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Cache;
import ru.spbstu.icc.kspt.graphicEditor.core.model.Desk;
import ru.spbstu.icc.kspt.graphicEditor.core.model.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.model.instruments.*;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Класс {@link PaintController} реализует взаимодействие объектов рисования: доски и инструментов
 */
public class PaintController {

    private MainApp app;

    private Color activeColor = Color.BLACK;
    private Instrument activeInstrument;
    private double activeWidth;

    private Image editIcon;
    private PaintedElement elementToEdit;
    private boolean editing = false;
    private Point refPoint = new Point(0, 0);
    private Point curPoint;

    private Instrument brush;
    private Instrument line;
    private Instrument rectangle;

    private Desk desk;
    private Cache cache = new Cache();

    @FXML
    private Canvas deskCanvas = new Canvas();
    private GraphicsContext deskGC;

    @FXML
    private ImageView instrumentImage = new ImageView();
    @FXML
    private TextField widthSetter = new TextField();

    @FXML
    private Button editButton = new Button();
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
            //Режим редактирования
            editIcon = new Image("/icons/editIcon.png");
            setButtonIcon(editButton, editIcon);

            //Кисть
            Image brushIcon = new Image("/icons/brushIcon.png");
            brush = new Brush(brushIcon);
            setButtonIcon(brushButton, brushIcon);

            //Линия
            Image lineIcon = new Image("/icons/lineIcon.png");
            line = new Line(lineIcon);
            setButtonIcon(lineButton, lineIcon);
            //TODO
            //Прямоугольник


        } catch (IllegalArgumentException e){
            System.err.println("PaintController.initInstruments(): " + e);
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
        deskGC.setFill(activeColor);
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
        editButton.setOnAction(event -> {
            editing = true;
            instrumentImage.setImage(editIcon);
        });

        brushButton.setOnAction(event -> {
            activeInstrument = brush;
            editing = false;
            instrumentImage.setImage((Image) activeInstrument.getIcon());
        });

        lineButton.setOnAction(event -> {
            activeInstrument = line;
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

        deskCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            curPoint = new Point(event.getX(), event.getY());
            setCoordsLabelText(event);
        });

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

        //TODO
        deskCanvas.setOnKeyTyped(event -> {
            if (event.getCode() == KeyCode.DELETE) {

                desk.findPainted(curPoint);
                repaint();
            }
            System.err.println("TYPED");
        });
    }

    //Нажатие кнопки мыши
    private void onMousePressed(MouseEvent event){
        //Точка отсчета для действий в режиме редактирования
        refPoint = new Point(event.getX() , event.getY());

        if(editing){
            elementToEdit = desk.findPainted(refPoint);

        } else {
            activeInstrument.onPressed(refPoint, activeWidth);
            paintElement(activeInstrument.getPE());
        }
    }

    //Перемещение с зажатой кнопкой мыши
    private void onMouseDragged(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

        //В режиме редактирования
        if(elementToEdit != null && editing) {
            //Левая кнопка мыши: перемещение
            if (event.getButton() == MouseButton.PRIMARY) {
                elementToEdit.translate(x - refPoint.getX(), y - refPoint.getY());
                repaint();
                paintElement(elementToEdit);
                refPoint = new Point(x ,y);
            }

            //Средняя кнопка мыши: вращение
            else if (event.getButton() == MouseButton.MIDDLE) {
                elementToEdit.rotate(0.031415 * (refPoint.getX() - x));
                repaint();
                paintElement(elementToEdit);
                refPoint = new Point(x ,y);
            }

            //Правая кнопка мыши: масштабирование
            else if (event.getButton() == MouseButton.SECONDARY) {
                double cx = elementToEdit.getCenter().getX();
                double cy = elementToEdit.getCenter().getY();

                double kx = Math.abs(x - cx) / Math.abs(refPoint.getX() - cx);
                double ky = Math.abs(y - cy) / Math.abs(refPoint.getY() - cy);

                elementToEdit.scale(kx, ky);
                repaint();
                paintElement(elementToEdit);
                refPoint = new Point(x ,y);
            }

        //В режиме рисования
        } else if(!editing) {
            repaint();
            activeInstrument.onDragged(new Point(x, y));
            paintElement(activeInstrument.getPE());
        }
    }

    //Отпускание кнопки мыши
    private void onMouseReleased(){
        //В режиме редактирования: завершить действие и вернуть измененный объект в список
        if(editing && elementToEdit != null){
            desk.addElement(elementToEdit);
            elementToEdit = null;

        //В режиме рисования: добавить объект в список
        } else if(!editing){
            activeInstrument.onReleased();
            desk.addElement(activeInstrument.getPE());
        }
    }

    private void onMouseMoved(MouseEvent event){

    }

    private void paintElement(PaintedElement element){
        if(element.getClass() == BrushElement.class){
            for(Point p : element.getPoints()){
                deskGC.fillOval(p.getX(), p.getY(), element.getWidth(), element.getWidth());
            }
        }

        if(element.getClass() == LineElement.class){
            Point start = element.getPoints().get(0);
            Point end = element.getPoints().size() > 1 ? element.getPoints().get(1) : start;
            deskGC.setLineWidth(activeWidth);
            deskGC.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
        }
    }

    private void repaint(){
        repaintBackground();
        if(desk.getPE().size() > 0){
            for(PaintedElement e : desk.getPE()){
                paintElement(e);
            }
        }
    }

    private void repaintBackground(){
        deskGC.setFill((Color) desk.getBackgroundColor());
        deskGC.fillRect(0, 0, desk.getWidth(), desk.getHeight());
        deskGC.setFill(activeColor);
    }

    public double getActiveWidth(){
        activeWidth = Integer.parseInt(widthSetter.getText());
        return activeWidth;
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
