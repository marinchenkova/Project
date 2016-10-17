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

//TODO данный класс не должен быть наследником MainApp
//TODO

public class Instrument extends MainApp {

    //TODO убрать ненужные комментарии
    /**
     * Поля
     */

    /**
     * Класс - контроллер
     */
    private  Controller controller;

    //TODO вроде использовать в классе его наследников - это антипаттерн
    /**
     * Классы - наследники
     */
    private Brush brush;
    private Fill fill;
    private Text text;
    private Eraser eraser;
    private Pipette pipette;
    private Zoom zoom;
    private Figure figure;
    private Palette palette;
    private Desk desk;
    private SettingsPanel settingsPanel;

    /**
     * Доска
     */
    protected Canvas deskCanvas;

    /**
     * Активные значения
     */
    protected static Instrument activeInstrument;
    protected static Color activeColor;
    protected static Color backgroundColor;
    protected static int lineWidth;
    private static ImageView instrumentImage;

    /**
     * Другие
     */
    protected static TextField widthSetter;

    //TODO пустой конструктор не за чем

    /**
     * Конструктор
     */
    public Instrument() {

    }

    public Instrument(Controller controller) {
        this.controller = controller;
        initialize();
    }
    //TODO данный метод вроде как можно сделать private. Или раз используется в наследнике, то protected.
    /**
     * Инициализация
     */
    public void initialize(){
        //TODO длинноват метод, хотя в нем и все иициализируется.
        //TODO по идее, когда изменится дизайн, такого не будет

        //TODO findbugs тут очобенно сильно ругается. типа поля никогда не читаются. их надо убрать из класса.
        //Настройка по умолчанию

        backgroundColor = Color.WHITE;

        activeInstrument = brush;
        activeColor = Color.BLACK;
        instrumentImage = controller.instrumentImage;


        //Создание всех объектов классов-наследников
        desk = new Desk(controller);
        settingsPanel = new SettingsPanel(controller);
        palette = new Palette(controller);

        brush = new Brush(controller);
        eraser = new Eraser(controller);
        fill = new Fill(controller);
        text = new Text(controller);
        pipette = new Pipette(controller);
        zoom = new Zoom(controller);

        figure = new Figure(controller);
    }


    /**
     * Применение иснтрумента
     */
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
       activeInstrument.instrumentAction(event, graphicsContext);
    }

    /**
     * Установка иконки на кнопку
     */
    public void setIcon(Button button, Image buttonIcon) {
        button.setPadding(Insets.EMPTY);
        button.setGraphic(new ImageView(buttonIcon));
    }

    /**
     * Установка иконки активного инструмента
     */
    public void setInstrumentIcon(Image instrumentIcon){
        instrumentImage.setImage(instrumentIcon);
    }

    /**
     * Выбрать курсор, который будет отображаться на доске
     */
    public void setCursor(Canvas deskCanvas, Cursor cursorImage){
        deskCanvas.setCursor(cursorImage);
    }
}

