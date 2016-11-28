package ru.spbstu.icc.kspt.graphicEditor.app;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import ru.spbstu.icc.kspt.graphicEditor.core.*;
import ru.spbstu.icc.kspt.graphicEditor.core.instruments.*;
import ru.spbstu.icc.kspt.graphicEditor.core.paintedElements.*;
import ru.spbstu.icc.kspt.graphicEditor.core.util.color.*;
import ru.spbstu.icc.kspt.graphicEditor.core.util.mouse.*;

/**
 * Класс {@link Controller} реализует связь GUI с логикой приложения.
 */
public class Controller {

    @FXML
    private Canvas deskCanvas = new Canvas();

    @FXML
    private Button brushButton = new Button();
    @FXML
    private Button agentButton = new Button();
    @FXML
    private Button rectangleButton = new Button();
    @FXML
    private Button lineButton = new Button();

    @FXML
    private Label coordsLabel = new Label();
    @FXML
    private Label sizeLabel = new Label();
    @FXML
    private ImageView coordsImage = new ImageView();
    @FXML
    private ImageView sizeImage = new ImageView();
    @FXML
    private ImageView instrumentImage = new ImageView();
    @FXML
    private TextField widthSetter = new TextField();

    public Controller() {}

    /**
     * Инициализация
     */
    @FXML
    public void initialize() throws Exception {

    }
}
