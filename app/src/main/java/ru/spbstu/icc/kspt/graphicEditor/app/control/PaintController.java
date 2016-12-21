package ru.spbstu.icc.kspt.graphicEditor.app.control;

import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

import javafx.scene.input.*;
import javafx.scene.paint.Color;
import ru.spbstu.icc.kspt.graphicEditor.app.MainApp;
import ru.spbstu.icc.kspt.graphicEditor.app.view.*;
import ru.spbstu.icc.kspt.graphicEditor.core.model.*;
import ru.spbstu.icc.kspt.graphicEditor.core.model.instruments.*;


/**
 * Класс {@link PaintController} реализует взаимодействие объектов рисования: доски и инструментов
 */
public class PaintController{

    private MainApp app;

    protected static Color activeColor = Color.BLACK;
    protected static Instrument activeInstrument;
    protected static double activeWidth;
    protected static boolean editing = false;

    protected static Instrument brush = new Brush("/icons/brushIcon.png");
    protected static Instrument line = new Line("/icons/lineIcon.png");
    protected static Instrument rect = new Rectangle("/icons/rectangleIcon.png");

    private static Initializer initializer;
    protected static Painter painter;

    protected static Desk desk;

    @FXML
    private Canvas deskCanvas = new Canvas();

    @FXML
    private ImageView instrumentImage = new ImageView();
    @FXML
    private TextField widthSetter = new TextField();

    //Кнопки
    @FXML
    private Button editButton = new Button();
    @FXML
    private Button brushButton = new Button();
    @FXML
    private Button lineButton = new Button();
    @FXML
    private Button rectButton = new Button();

    @FXML
    private Label coordsLabel = new Label();
    @FXML
    private Label sizeLabel = new Label();
    @FXML
    private ImageView coordsImage = new ImageView();
    @FXML
    private ImageView sizeImage = new ImageView();

    public PaintController() {}

    /**
     * Инициализация средств рисования
     */
    @FXML
    public void initialize() throws Exception {
        //Активный инструмент по умолчанию
        activeInstrument = brush;

        painter = new Painter(this);
        initializer = new Initializer(this);
    }

    /**
     * Обработка событий мыши и нажатия клавиш.
     */
    public void paint(){
        deskCanvas.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> initializer.setCoordsLabelText(event));

        deskCanvas.addEventHandler(MouseEvent.MOUSE_EXITED, event -> initializer.setCoordsLabelText(null));

        deskCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            painter.onMouseMoved(event);
            initializer.setCoordsLabelText(event);
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            initializer.setActiveWidth();
            painter.onMousePressed(event);
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            painter.onMouseReleased();
        });

        deskCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            painter.onMouseDragged(event);
            initializer.setCoordsLabelText(event);
        });

        //Удаление элемента
        app.getPrimaryStage().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (editing && event.getCode() == KeyCode.DELETE) {
                painter.deleteElement();
            }
        });

        //Обработка отмены действия (CTRL + Z)
        app.getPrimaryStage().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.CONTROL) {
                app.getPrimaryStage().addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                    if (e.getCode() == KeyCode.Z) {
                        painter.undo();
                    }
                });
            }
        });
    }

    public void setApplication(MainApp mainApp){ app = mainApp; }

    public Button getEditButton(){ return editButton; }
    public Button getBrushButton(){ return brushButton; }
    public Button getLineButton(){ return lineButton; }
    public Button getRectButton(){ return rectButton; }

    public ImageView getCoordsImage(){ return coordsImage; }
    public ImageView getSizeImage(){ return sizeImage; }
    public ImageView getInstrumentImage(){ return instrumentImage; }

    public Label getCoordsLabel(){ return coordsLabel; }
    public Label getSizeLabel(){ return  sizeLabel; }

    public TextField getWidthSetter(){ return widthSetter; }

    public Canvas getDeskCanvas(){ return deskCanvas; }
}
