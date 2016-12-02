package ru.spbstu.icc.kspt.graphicEditor.app.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.spbstu.icc.kspt.graphicEditor.app.PaintController;

import java.io.IOException;

/**
 * Графический редактор
 * @author Маринченко В. А.
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        initRootLayout();

        initPaintLayout();
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
            System.err.println(this + ": " + e);
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

            PaintController pController = loader.getController();
            pController.setApplication(this);

        } catch (Exception e) {
            System.err.println(this + ": " + e);
        }
    }

    public static void main(String[] args) { launch(args); }
}
