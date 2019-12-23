package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.*;

public class Controller {
    @FXML
    private TextField userName;
    @FXML
    private Button logining;
    @FXML
    private TextField password;
    @FXML
    private TextField help;
    private String filePath = "C:/User/.yang/";

    @FXML
    protected void loginButtonAction(ActionEvent event) {

        Pane pane = new Pane();
        Circle[][] circles = new Circle[6][6];
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                circles[i][j] = new Circle(i * 100 + 50, j * 100 + 50, 45);
                circles[i][j].setFill(new Color(1, 1, 1, 0));
                circles[i][j].setStroke(Color.BLACK);
                int finalI = i;
                int finalJ = j;
                circles[i][j].setOnMousePressed(e -> {
                    System.out.println(finalI + " " + finalJ);
                });
                pane.getChildren().add(circles[i][j]);
            }
        }

        String username = userName.getText().trim();
        String pwd = password.getText().trim();
        userName.getFont();
        logining.setDefaultButton(false);
        File file = new File(filePath + "picturePWD.txt");
        if (file.exists()) {
            try (BufferedReader writer = new BufferedReader(new FileReader(filePath + "picturePWD.txt"))) {
                String uName = writer.readLine();
                String pw = writer.readLine();
                Boolean loginFlag = true;
                if (!username.equals(uName)) {
                    loginFlag = false;
                    help.setVisible(true);
                    help.setManaged(true);
                    help.setText("用户名错误");
                }
                if (!pwd.equals(pw) && loginFlag) {
                    loginFlag = false;
                    help.setVisible(true);
                    help.setManaged(true);
                    help.setText("用户密码错误");
                }
                if (loginFlag) {
                    help.setVisible(true);
                    help.setManaged(true);
                    help.setText("登录成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            help.setVisible(true);
            help.setManaged(true);
            help.setText("文件用户不存在");
        }
//        if (1 == 1) {
//            help.setVisible(true);
//            help.setManaged(true);
//            help.setText("文件不存在");
//        }
    }

    @FXML
    protected void loginUPBUttonAction(ActionEvent event) {
        String username = userName.getText().trim();
        String pwd = password.getText().trim();
        File file = new File(filePath);
        if (file.exists()) {
            help.setVisible(true);
            help.setManaged(true);
            help.setText("文件存在");
        } else {
            file.mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + "picturePWD.txt", true))) {
                writer.write(username);
                writer.newLine();
                writer.write(pwd);
                writer.newLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            help.setVisible(true);
            help.setManaged(true);
            help.setText("创建文件成功" + filePath);
        }
        userName.getFont();
        logining.setDefaultButton(false);
        if (1 == 1) {
        }
    }
}
