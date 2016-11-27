package ru.spbstu.icc.kspt.graphicEditor.view.control;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


/**
 * Контроллер класс реализует связь GUI с логикой приложения
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
    public void initialize() throws Exception {

    }
}
