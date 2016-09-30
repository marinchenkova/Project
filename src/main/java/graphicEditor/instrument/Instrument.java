package graphicEditor.instrument;

import graphicEditor.MainApp;
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

    //Кнопка инструмента
    public static class Buttons extends Button {

        private static Image buttonIcon;

        //Установка иконки
        @FXML
        public static void initIcon(final Button button) {
            buttonIcon = new Image("/images/buttons/" + button.getId() + ".png");
            button.setPadding(Insets.EMPTY);
            button.setGraphic(new ImageView(buttonIcon));
        }
    }
}
