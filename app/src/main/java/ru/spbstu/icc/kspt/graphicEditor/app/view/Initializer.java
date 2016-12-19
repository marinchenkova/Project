package ru.spbstu.icc.kspt.graphicEditor.app.view;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.spbstu.icc.kspt.graphicEditor.app.control.PaintController;
import ru.spbstu.icc.kspt.graphicEditor.core.model.Desk;

/**
 * @author Marinchenko V. A.
 */
public class Initializer extends PaintController{

    private PaintController controller;

    private Canvas deskCanvas;

    private Button editButton;
    private Button brushButton;
    private Button lineButton;
    private Button rectButton;

    private ImageView instrumentImage;

    private Label coordsLabel;
    private Label sizeLabel;

    private TextField widthSetter;

    private Image editIcon = new Image("/icons/editIcon.png");
    private Image brushIcon = new Image("/icons/brushIcon.png");
    private Image lineIcon = new Image("/icons/lineIcon.png");
    private Image rectIcon = new Image("/icons/rectangleIcon.png");

    public Initializer(PaintController controller){
        this.controller = controller;
        initLinks();
        initDesk();
        initInfo();
        onSettingsChanged();
    }

    public void initLinks(){
        deskCanvas = controller.getDeskCanvas();

        editButton = controller.getEditButton();
        brushButton = controller.getBrushButton();
        lineButton = controller.getLineButton();
        rectButton = controller.getRectButton();

        instrumentImage = controller.getInstrumentImage();

        coordsLabel = controller.getCoordsLabel();
        sizeLabel = controller.getSizeLabel();

        widthSetter = controller.getWidthSetter();

        setButtonIcon(editButton, editIcon);
        setButtonIcon(brushButton, brushIcon);
        setButtonIcon(lineButton, lineIcon);
        setButtonIcon(rectButton, rectIcon);

        controller.getCoordsImage().setImage(new Image("/icons/coordsIcon.png"));
        controller.getSizeImage().setImage(new Image("/icons/sizeIcon.png"));
    }

    public void initDesk(){
        desk = new Desk((int) deskCanvas.getWidth(), (int) deskCanvas.getHeight(), Color.WHITE);
        painter.repaintBackground();
    }

    public void initInfo() {
        deskCanvas.getGraphicsContext2D().setFill(activeColor);
        activeWidth = Integer.parseInt(widthSetter.getText());
        coordsLabel.setText("");
        sizeLabel.setText(desk.getSizeString());

        instrumentImage.setImage(new Image((String) activeInstrument.getIcon()));
    }

    /**
     * Обработка нажатия кнопок и изменения ширины линии
     */
    public void onSettingsChanged(){
        editButton.setOnAction(event -> {
            editing = true;
            instrumentImage.setImage(editIcon);
        });

        brushButton.setOnAction(event -> {
            activeInstrument = brush;
            editing = false;
            instrumentImage.setImage(new Image((String) activeInstrument.getIcon()));
        });

        lineButton.setOnAction(event -> {
            activeInstrument = line;
            editing = false;
            instrumentImage.setImage(new Image((String) activeInstrument.getIcon()));
        });

        rectButton.setOnAction(event -> {
            activeInstrument = rect;
            editing = false;
            instrumentImage.setImage(new Image((String) activeInstrument.getIcon()));
        });

        widthSetter.setOnAction(event -> setActiveWidth());
    }

    public void setCoordsLabelText(MouseEvent event){
        if(event != null){
            coordsLabel.setText((int) event.getX() + ", " + (int) event.getY());
        } else coordsLabel.setText("");
    }


    public void setActiveWidth(){
        activeWidth = Integer.parseInt(widthSetter.getText());
        deskCanvas.getGraphicsContext2D().setLineWidth(activeWidth);
    }


    public void setSize(int w, int h){
        desk.setSize(w, h);

        deskCanvas.setWidth(w);
        deskCanvas.setHeight(h);

        sizeLabel.setText(desk.getSizeString());
    }

    public static void setButtonIcon(Button button, Image image){
        button.setPadding(new Insets(0, 0, 0, 0));
        button.setGraphic(new ImageView(image));
    }

}
