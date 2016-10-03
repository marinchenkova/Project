package graphicEditor.instrument;

import graphicEditor.Controller;
import graphicEditor.MainApp;
import graphicEditor.instrument.figure.Figure;
import graphicEditor.instrument.mainInstruments.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Инструмент
 */
public class Instrument extends MainApp {
    //Поля
    private  Controller controller;

    //Классы - наследники
    private Brush brush;
    private Fill fill;
    private Text text;
    private Eraser eraser;
    private Pipette pipette;
    private Zoom zoom;
    private Figure figure;

    //Конструктор
    public Instrument() {

    }

    public Instrument(Controller controller) {
        this.controller = controller;
        initialize();
    }

    //Инициализация
    public void initialize(){
        brush = new Brush(controller);
        fill = new Fill(controller);
        text = new Text(controller);
        eraser = new Eraser(controller);
        pipette = new Pipette(controller);
        zoom = new Zoom(controller);
        figure = new Figure(controller);
    }

    //Установка иконки на кнопку
    public void setIcon(Button button, Image buttonIcon) {
        button.setPadding(Insets.EMPTY);
        button.setGraphic(new ImageView(buttonIcon));
    }

}

