package ru.spbstu.icc.kspt.graphicEditor.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.spbstu.icc.kspt.graphicEditor.app.control.PaintController;

import java.io.IOException;

/**
 * Графический редактор. Используется векторная графика: нарисованные элементы можно редактировать.
 * @author Маринченко В. А.
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private PaintController pController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        initRootLayout();
        initPaintLayout();

        pController.paint();
    }

    /**
     * Инициализация главного окна приложения.
     * @throws IOException ошибка в выполнении FXMLLoader.load()
     * @throws IllegalStateException неверный путь к файлу FXML
     */
    private void initRootLayout(){
        try {
            //Загрузка FXML файла
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/fxml/rootLayout.fxml"));
            rootLayout = loader.load();

            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
            primaryStage.setTitle("New picture : Graphic Editor Java 8");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализация окна рисования.
     * @throws IOException ошибка в выполнении FXMLLoader.load()
     * @throws IllegalStateException неверный путь к файлу FXML
     */
    private void initPaintLayout(){
        try {
            //Загрузка FXML файла
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/fxml/paintLayout.fxml"));
            AnchorPane paintLayout = loader.load();
            rootLayout.setCenter(paintLayout);

            pController = loader.getController();
            pController.setApplication(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Возврат главной сцены.
     * @return главная сцена
     */
    public Stage getPrimaryStage(){ return primaryStage; }

    /**
     * Точка входа в приложение.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) { launch(args); }
}
