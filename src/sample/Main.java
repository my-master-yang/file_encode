package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private Text help;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("图片加密");
        primaryStage.setScene(new Scene(root, 900, 900));
        primaryStage.show();

//        gotologin();

    }

    /**
     * 登录
     */
    public void gotologin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("login.fxml");
            login.loginButtonAction();
//            LoginController view= (LoginController) replaceSceneContent("sample.fxml");
//            view.chooseDest();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 实例化登录窗口
     *
     * @param fxml
     * @return
     * @throws Exception
     */
    public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
        GridPane page;
        try {
            page = (GridPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 344, 366);
        Stage stage = new Stage();
        stage.setTitle("图片加密");
        stage.setScene(scene);
        stage.sizeToScene();
        return loader.getController();
    }

}

