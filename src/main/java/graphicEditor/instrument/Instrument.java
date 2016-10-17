package graphicEditor.instrument;

import graphicEditor.Controller;
import graphicEditor.MainApp;
import graphicEditor.instrument.desk.Desk;
import graphicEditor.instrument.figures.Figure;
import graphicEditor.instrument.mainInstruments.*;
import graphicEditor.instrument.palette.Palette;
import graphicEditor.instrument.settingsPanel.SettingsPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Инструмент - объект, который содержит все инструменты
 */
public class Instrument {

    private Controller controller;
    protected static Canvas deskCanvas;

    /**
     * Активные значения - общие переменные для классов-наследников, которые изменяются при работе программы
     */
    protected static Instrument activeInstrument;
    protected static Color activeColor;
    protected static Color backgroundColor;
    protected static double lineWidth;
    protected static TextField widthSetter;
    private static ImageView instrumentImage;

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

        //Создание всех объектов классов-наследников
        new Palette(controller);
        new Desk(controller);
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
}

