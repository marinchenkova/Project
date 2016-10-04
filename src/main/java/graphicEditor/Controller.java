package graphicEditor;

import graphicEditor.instrument.desk.Desk;
import graphicEditor.instrument.Instrument;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;


/**
 * Контроллер для MainApp
 * @author Маринченко В. А.
 */
public class Controller {
    /**
    * Поля
    */
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
     * Доска
     */
    @FXML
    public Canvas desk = new Canvas();

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
