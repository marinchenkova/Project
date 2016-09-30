package graphicEditor;

import graphicEditor.instrument.Instrument;
import graphicEditor.instrument.mainInstruments.Brush;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * Контроллер для MainApp
 * @author Маринченко В. А.
 */
public class Controller {

    @FXML
    private Button brushButton;
    @FXML
    private Button eraserButton;
    @FXML
    private Button fillButton;
    @FXML
    private Button textButton;

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
        Brush.Buttons.initIcon(brushButton);
        Brush.Buttons.initIcon(fillButton);
        Brush.Buttons.initIcon(textButton);
        Brush.Buttons.initIcon(eraserButton);

    }

}
