package graphicEditor;

import graphicEditor.instrument.Instrument;
import graphicEditor.instrument.mainInstruments.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;


/**
 * Контроллер для MainApp
 * @author Маринченко В. А.
 */
public class Controller {
    //Поля
    private Instrument instrument;
    private Brush brush;

    //Ссылки на объекты в FXML
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
     * Конструктор
     */
    public Controller() {

    }

    /**
     * Инициализация класса-контроллера
     */
    @FXML
    public void initialize() throws Exception {
        instrument = new Instrument(this);
    }



}
