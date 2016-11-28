package ru.spbstu.icc.kspt.graphicEditor.app.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Графический редактор
 * @author Маринченко В. А.
 */
public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initializeFXMLForm();
    }

    /**
     * Инициализация GUI формы
     * @throws IOException ошибка в выполнении FXMLLoader.load()
     * @throws IllegalStateException неверный путь к файлу FXML
     */
    private void initializeFXMLForm() {
        try {
            //Загрузка GUI формы
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/fxml/graphicEditor.fxml"));
            Parent rootLayout = loader.load();

            //Создание сцены и сообщение GUI формы в сцену
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
            primaryStage.setTitle("New picture");
        } catch (IllegalStateException e){
            System.err.println(this + ": wrong path to FXML");
        } catch (IOException e) {
            System.err.println(this + ": IOException in FXMLLoader.load()");
        }
    }

    public static void main(String[] args) { launch(args); }
}
