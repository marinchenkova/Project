package graphicEditor.instrument;

import graphicEditor.Controller;
import graphicEditor.MainApp;
import graphicEditor.instrument.desk.Desk;
import graphicEditor.instrument.figures.Figure;
import graphicEditor.instrument.mainInstruments.*;
import graphicEditor.instrument.palette.Palette;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Инструмент
 */
public class Instrument extends MainApp {
    //Поля
    private  Controller controller;

    //Классы - наследники
    public Brush brush;
    private Fill fill;
    private Text text;
    private Eraser eraser;
    private Pipette pipette;
    private Zoom zoom;
    private Figure figure;
    private Palette palette;

    /**
     * Доска
     */
    protected Desk desk;
    protected Canvas deskCanvas;
    protected static Instrument activeInstrument;
    protected static Color activeColor;
    protected static Color backgroundColor;

    //Конструктор
    public Instrument() {

    }

    public Instrument(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        desk = new Desk(controller);
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
     * Установка иконки на кнопку
     */
    public void setIcon(Button button, Image buttonIcon) {
        button.setPadding(Insets.EMPTY);
        button.setGraphic(new ImageView(buttonIcon));
    }

    /**
     * Выбрать курсор, который будет отображаться на доске
     */
    public  void  setCursor(Canvas deskCanvas, Cursor cursorImage){
        deskCanvas.setCursor(cursorImage);
    }

    /**
     * Применение иснтрумента
     */
    public void instrumentAction(MouseEvent event, GraphicsContext graphicsContext){
       activeInstrument.instrumentAction(event, graphicsContext);
    }

}

