package graphicEditor;

import graphicEditor.instrument.Instrument;
import graphicEditor.instrument.mainInstruments.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * Контроллер для MainApp
 * @author Маринченко В. А.
 */
public class Controller {

    public Controller controller;

    //Ссылки на объекты в форме FXML
    @FXML
    private Button brushButton;
    @FXML
    private Button fillButton;
    @FXML
    private Button textButton;
    @FXML
    private Button eraserButton;
    @FXML
    private Button pipetteButton;
    @FXML
    private Button zoomButton;
    @FXML
    private Button rectangleButton;
    @FXML
    private Button ellipseButton;
    @FXML
    private Button lineButton;



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
        Instrument.setIcon(brushButton);
        Instrument.setIcon(fillButton);
        Instrument.setIcon(textButton);
        Instrument.setIcon(eraserButton);
        Instrument.setIcon(pipetteButton);
        Instrument.setIcon(zoomButton);
        Instrument.setIcon(rectangleButton);
        Instrument.setIcon(ellipseButton);
        Instrument.setIcon(lineButton);

    }



}
