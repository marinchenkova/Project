package graphicEditor;

import graphicEditor.core.instrument.Instrument;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


/**
 * Контроллер для класса {@link MainApp} - главного класса приложения.<br>
 * Данный класс реализует связь объектов GUI формы с объектами логики приложения.<br>
 * Методы:
 *
 * @author Маринченко В. А.
 */
public class Controller {

    @FXML
    private Canvas deskCanvas = new Canvas();
    @FXML
    private Button brushButton = new Button();
    @FXML
    private Button eraserButton = new Button();
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

    //TODO Как сделать e.g. getButton(brush)?
    public Canvas getDeskCanvas(){
        return deskCanvas;
    }
    public Button getBrushButton(){
        return brushButton;
    }
    public Button getEraserButton(){
        return eraserButton;
    }
    public Button getRectangleButton(){
        return rectangleButton;
    }
    public Button getLineButton(){
        return lineButton;
    }
    public Label getCoordsLabel(){
        return coordsLabel;
    }
    public Label getSizeLabel(){
        return sizeLabel;
    }
    public ImageView getCoordsImage(){
        return coordsImage;
    }
    public ImageView getSizeImage(){
        return sizeImage;
    }
    public ImageView getInstrumentImage(){
        return instrumentImage;
    }
    public TextField getWidthSetter(){
        return widthSetter;
    }

}
