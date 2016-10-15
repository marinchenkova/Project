package graphicEditor.instrument.settingsPanel;

import graphicEditor.Controller;
import graphicEditor.instrument.Instrument;
import javafx.event.*;

/**
 * Панель настроек инструментов
 */
public class SettingsPanel extends Instrument {
    /**
     * Поля
     */

    /**
     * Класс - контроллер
     */
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
    public void initialize(){
        widthSetter = controller.widthSetter;
        lineWidth = Integer.parseInt(widthSetter.getText());

        run();
    }

    /**
     * Выполнение
     */
    public void run(){
        //Работа с шириной инструмента
        widthSetter.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                lineWidth = Integer.parseInt(widthSetter.getText());
            }
        });
    }
}
