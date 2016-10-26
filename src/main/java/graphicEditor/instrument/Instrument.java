package graphicEditor.instrument;

import graphicEditor.Controller;
import graphicEditor.instrument.desk.Desk;
import graphicEditor.instrument.figures.Figure;
import graphicEditor.instrument.paintedElements.BrushElement;
import graphicEditor.instrument.mainInstruments.*;
import graphicEditor.instrument.palette.Palette;
import graphicEditor.instrument.settingsPanel.SettingsPanel;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Инструмент - объект, который содержит все инструменты
 */
public class Instrument {

    private Controller controller;


    /**
     * Активные значения - общие переменные для классов-наследников, которые изменяются при работе программы
     */
    protected static Instrument activeInstrument;
    protected static Color activeColor;
    protected static Color backgroundColor;
    protected static double lineWidth;
    protected static TextField widthSetter;
    protected static Canvas deskCanvas;
    protected static Desk desk;
    private static ImageView instrumentImage;

    protected static boolean onBrushObject;
    protected static boolean dragStarted = false;
    protected static boolean dragEnded = false;
    protected static List<BrushElement> brushElements;
    //TODO как обойтись без пустого конструктора?

    /**
     * Конструктор: параметр controller передается для допуска к классу Controller
     * @param controller
     */
    public Instrument(Controller controller) {
        this.controller = controller;
        initialize();
    }
    public Instrument(){

    }

    /**
     * Инициализация - cоздание объектов классов-наследников
     */
    private void initialize(){
        deskCanvas = controller.getDeskCanvas();
        instrumentImage = controller.getInstrumentImage();
        brushElements = new ArrayList<BrushElement>();

        //Создание всех объектов классов-наследников
        new Palette(controller);
        desk = new Desk(controller);
        new SettingsPanel(controller);
        new Brush(controller);
        new Eraser(controller);
        new Fill(controller);
        new Text(controller);
        new Pipette(controller);
        new Zoom(controller);
        new Figure(controller);

    }

    /**
     * Применение иснтрумента - вызывается из событий event объекта desk. Выполняет этот же метод, но переписанный,
     * для каждого инструмента.
     * @param event
     */
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
       activeInstrument.instrumentAction(event, graphicsContext);
    }

    protected static void setActiveInstrument(Instrument instrument){
        activeInstrument = instrument;
    }

    protected void setIcon(Button button, Image buttonIcon) {
        button.setPadding(Insets.EMPTY);
        button.setGraphic(new ImageView(buttonIcon));
    }

    /**
     * Установка иконки активного инструмента в панель настроек
     */
    protected void setActiveInstrumentIcon(Image instrumentIcon){
        instrumentImage.setImage(instrumentIcon);
    }

    protected void setDeskCursor(Canvas deskCanvas, Cursor cursorImage){
        deskCanvas.setCursor(cursorImage);
    }

    public void findPainted(Integer mx, Integer my){
        for (int i = 0; i < brushElements.size(); i++){
            brushElements.get(i).isBrushElement(mx, my);
        }
    }
}

