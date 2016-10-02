package graphicEditor.instrument;

import graphicEditor.Controller;
import graphicEditor.MainApp;
import graphicEditor.instrument.mainInstruments.Brush;
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

    //Классы - наследники
    protected Brush brush;

    //Конструктор
    public Instrument() {

    }


    //Установка иконки на кнопку
    public static void setIcon(Button button) {
        Image buttonIcon;
        buttonIcon = new Image("/images/buttons/" + button.getId() + ".png");
        button.setPadding(Insets.EMPTY);
        button.setGraphic(new ImageView(buttonIcon));
    }

}

