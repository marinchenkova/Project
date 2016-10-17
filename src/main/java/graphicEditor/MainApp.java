package graphicEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

// Нет разделения логики приложния от взаимодействия с пользователем. Не знаю, как бы тут это все разделить.
// Наверное, все можно оставить, потому что вроде логика вся в javafx реализована.
// Но дизайн поменять точно нужно.
// Приложение прикольное. Велью - есть. Но код пока не очень, что в принципе нормально пока.
// Стоит работать в направлении улучшении кода.

//TODO посмотреть на все предупреждения идеи. Проигнорировать быть может всякие it can be package-private.
//TODO поставить findbugs в идею. Пусть он проанализирует. Мне выдал 39 разных bug items по стилю, производительности, дизайну и пр.

//TODO не нравится, что этот класс имеет наследников.
//TODO этот класс имеет метод main, который является точкой входа в программу.
//TODO и получается, что во всех его наследниках тоже есть этот метод. В таком приложении достаточно одной точки входа вроде
//TODO странно очень получается

//TODO нужно вынести из этого класса все, что нужно в наследниках в другой класс, который еще и следует поместить
//TODO на один пакетный уровень с ними или ниже. А в данном классе оставить main

//TODO нужно двигаться от общего к частному.

//TODO и не очень хорошо, что в классах, которые в пакетах уложены более глубоко, используются те, которые на самом верхнем уровне

//TODO неполный комментарий javadoc. либо поподробнее задокумнтировать, либо убрать вовсе
/**
 * Графический редактор
 * @author Маринченко В. А.
 */
public class MainApp extends Application {

    //TODO убрать бесполезный комментарий. Очевидно, что это поля.
    //TODO почитать "Чистый код" Р. Мартина, там много всего полезного. И про комментарии в том числе
    //TODO везде подумать над тем, нужен ли в данном месте комментарий. МБ понятно и без него. А мб лучше метод назвать
    //TODO так, чтобы все было понятно без комментария
    /**
     * Поля
     */
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
            //TODO вместо этого комментария можно вынести эти строчки в отдельный private метод. а тут вызвать.
            //TODO Метод назвать так, чтобы было понятно, что в нем происходит
            //TODO мб loadGUIForm()
            //TODO а так, вообще, для того, кто javafx знает, наверное и без выделения метода и комментария будет понятно
            //Загрузка GUI формы
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/fxml/graphicEditor.fxml"));
            Parent rootLayout = loader.load();

            //TODO аналогично, можно вынести в отдельный метод. А можно убрать комментарий просто.
            //Создание сцены и сообщение GUI формы в сцену
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
            primaryStage.setTitle("New picture");

        } catch (IOException error){
            error.printStackTrace();
        }
    }

    //TODO исправить докумнтирующий комментарий или убрать его
    /**
     * Возвращение главной сцены
     * @return
     */

    //TODO идея подсказывает, что этот метод не вызывается нигде.
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) { launch(args); }
}
