package graphicEditor.instrument.settingsPanel;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.event.*;

/**
 * Панель настроек инструментов
 */
public class SettingsPanel extends Instrument {

    private Controller controller;

    /**
     * Объекты панели настроек
     */

    /**
     * Конструктор
     */
    public SettingsPanel(Controller controller) {
        this.controller = controller;
        initialize();
    }

    /**
     * Инициализация
     */
    private void initialize(){
        widthSetter = controller.getWidthSetter();
        lineWidth = Integer.parseInt(widthSetter.getText());

        run();
    }

    /**
     * Выполнение
     */
    private void run(){
        //Работа с шириной инструмента
        widthSetter.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                lineWidth = Integer.parseInt(widthSetter.getText());
            }
        });
    }
}
