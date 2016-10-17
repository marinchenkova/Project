package graphicEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
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
     * @throws IOException
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
        } catch (IOException error){
            error.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(args); }
}
