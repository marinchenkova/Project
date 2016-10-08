package graphicEditor;

import graphicEditor.instrument.Instrument;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


/**
 * Контроллер для MainApp
 * @author Маринченко В. А.
 */
public class Controller {
    /**
    * Поля
    */

    /**
     * Доска
     */
    @FXML
    public Canvas deskCanvas = new Canvas();

    /**
     * Кнопки
     */
    @FXML
    public  Button brushButton = new Button();
    @FXML
    public Button fillButton = new Button();
    @FXML
    public Button textButton = new Button();
    @FXML
    public Button eraserButton = new Button();
    @FXML
    public Button pipetteButton = new Button();
    @FXML
    public Button zoomButton = new Button();
    @FXML
    public Button rectangleButton = new Button();
    @FXML
    public Button ellipseButton = new Button();
    @FXML
    public Button lineButton = new Button();

    /**
     * Информация
     */
    @FXML
    public Label coordsLabel = new Label();
    @FXML
    public ImageView coordsImage = new ImageView();

    //Классы наследники MainApp
    public Instrument instrument;

    /**
     * Конструктор
     */
    public Controller() {

    }

    /**
     * Инициализация класса-контроллера
     */
    @FXML
    public void initialize() throws Exception {
        //Создание всех объектов приложения
        instrument = new Instrument(this);
    }

}
