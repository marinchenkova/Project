package graphicEditor;

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
        initFXMLForm();
    }

    /**
     * Вызов GUI формы
     * @throws IOException
     */
    public void initFXMLForm() throws IOException{
        //Загрузка GUI формы
        Parent rootLayout = FXMLLoader.load(getClass().getResource("/fxml/graphicEditorForm.fxml"));

        //Создание сцены и сообщение GUI формы в сцену
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
