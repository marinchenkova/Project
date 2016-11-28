package ru.spbstu.icc.kspt.graphicEditor.app;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.*;

import ru.spbstu.icc.kspt.graphicEditor.app.view.MainApp;
import ru.spbstu.icc.kspt.graphicEditor.core.Instrument;
import ru.spbstu.icc.kspt.graphicEditor.core.instruments.*;

/**
 * Класс {@link PaintController} реализует взаимодействие объектов рисования: доски и инструментов
 */
public class PaintController {

    private MainApp app;

    private Instrument agent;
    private Instrument brush;

    //TODO список: карта курсоров
    private Cursor agentCursor;
    private Cursor brushCursor;

    @FXML
    private Canvas deskCanvas = new Canvas();

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
    private ImageView coordsImage = new ImageView();
    @FXML
    private ImageView sizeImage = new ImageView();

    public PaintController() {}

    /**
     * Инициализация
     */
    @FXML
    public void initialize() throws Exception {
        initInstruments();
    }

    /**
     * Инициализация инструментов: иконки кнопок, курсоров
     */
    private void initInstruments(){
        try{
            //Агент
            agent = new Agent();
            setButtonIcon(agentButton, "/icons/agentIcon.png");
            agentCursor = new ImageCursor(new Image("/cursors/agentCursor.png"), 2, 2);

            //Кисть
            brush = new Brush();
            setButtonIcon(brushButton, "/icons/brushIcon.png");
            brushCursor = new ImageCursor(new Image("/cursors/brushCursor.png"), 15, 15);
        } catch (IllegalArgumentException e){
            System.err.println(this + ": " + e);
        }

    }

    private void setButtonIcon(Button button, String path) throws IllegalArgumentException{
        button.setPadding(new Insets(0, 0, 0, 0));
        button.setGraphic(new ImageView(new Image(path)));
    }

    public void setApplication(MainApp mainApp){
        app = mainApp;
    }
}
