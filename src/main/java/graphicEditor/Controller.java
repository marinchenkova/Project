package graphicEditor;

import graphicEditor.instrument.Instrument;
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
    private Button fillButton = new Button();
    @FXML
    private Button textButton = new Button();
    @FXML
    private Button pipetteButton = new Button();
    @FXML
    private Button zoomButton = new Button();
    @FXML
    private Button rectangleButton = new Button();
    @FXML
    private Button ellipseButton = new Button();
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
     * Инициализация класса-контроллера - создание всех объектов приложения
     */
    @FXML
    public void initialize() throws Exception {
        new Instrument(this);
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
    public Button getFillButton(){
        return fillButton;
    }
    public Button getTextButton(){
        return textButton;
    }
    public Button getPipetteButton(){
        return pipetteButton;
    }
    public Button getZoomButton(){
        return zoomButton;
    }
    public Button getRectangleButton(){
        return rectangleButton;
    }
    public Button getEllipseButton(){
        return ellipseButton;
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
