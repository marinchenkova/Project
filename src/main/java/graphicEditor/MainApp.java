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
    //Поля
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
    public void initFXMLForm() {
        try {
            //Загрузка GUI формы
            Parent rootLayout = FXMLLoader.load(getClass().getResource
                    ("/fxml/graphicEditorForm.fxml"));

            //Создание сцены и сообщение GUI формы в сцену
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
            primaryStage.setTitle("New picture");
        } catch (IOException error){
            error.printStackTrace();
        }
    }

    /**
     * Возвращение главной сцены
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
