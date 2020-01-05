package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Utils.AESclass;
import sample.Utils.ExecutorUtil;

import java.io.*;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private TextField userName;
    @FXML
    private Button logining;
    @FXML
    private TextField password;
    @FXML
    private TextField help;

    @FXML
    Button oneFile;
    @FXML
    private TextField filPath;
    @FXML
    Button oneDest;
    @FXML
    private TextField destPath;
    @FXML
    Button EDCode;
    @FXML
    private TextField process;
    private Boolean encodeFlag = false;


    private String originFilePath;
    private String devFilePath;
    private String originDestPath;
    private String devDestPath;

    private String filePath = "C:/User/.yang/";
    private Stage stage;

    @FXML
    protected void loginButtonAction() {
        String username = userName.getText().trim();
        String pwd = password.getText().trim();
        userName.getFont();
        logining.setDefaultButton(false);
        File file = new File(filePath + "picturePWD.txt");
        if (file.exists()) {
            try (BufferedReader writer = new BufferedReader(new FileReader(filePath + "picturePWD.txt"))) {
//                String uName = writer.readLine();
                String uName = AESclass.decode(writer.readLine());
//                String pw = writer.readLine();
                Boolean loginFlag = true;
                String pw = AESclass.decode(writer.readLine());
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
//                    while (!s.equals("登录成功")) {
//
//                    }
                    stage = new Stage();
                    stage.setTitle("目录");
                    Parent foot1 = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
                    stage.setScene(new Scene(foot1, 1000, 750));
                    stage.show();
//                    Stage primaryStage = new Stage();
//                    primaryStage.setTitle("");
//                    Parent foot2 = FXMLLoader.load(getClass().getResource("login.fxml"));
//                    primaryStage.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            help.setVisible(true);
            help.setManaged(true);
            help.setText("文件用户不存在");
        }
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
//                writer.write(username);
                writer.write(AESclass.encode(username));
                writer.newLine();
//                writer.write(pwd);
                writer.write(AESclass.encode(pwd));
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

    @FXML
    protected void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
//                fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
//                fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.jpg"), new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            originFilePath = file.getAbsolutePath();
            filPath.setVisible(true);
            filPath.setManaged(true);
            filPath.setText(originFilePath);
            System.out.println(file.getAbsolutePath());
        }
    }

    @FXML
    protected void chooseDest() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Folder");
        File directory = directoryChooser.showDialog(stage);
        if (directory != null) {
            originDestPath = directory.getAbsolutePath();
            destPath.setVisible(true);
            destPath.setManaged(true);
            destPath.setText(originDestPath);
        }
    }

    @FXML
    protected void EDCodeAction(Event event) throws IOException {

            String devFilePath = filPath.getText().trim();
            String devDestPath = destPath.getText().trim();
            if (!devDestPath.equals(originDestPath) && originDestPath != null) {
                if (!encodeFlag) {
                    encodeFlag = true;
                    int index = 0;
                    ArrayList<Object> fileList = scanDest(originDestPath);
                    int Max = fileList.size();
                    for (Object a : fileList) {
                        System.out.println(a.toString());
                        index++;
                        String jindu = "完成" + index + "/" + Max;
                        process.appendText(jindu);
                        process.setText(jindu);

                        String dev = a.toString().replace(originDestPath, devDestPath);
                        File dest = new File(dev);
                        File fileParent = dest.getParentFile();
                        //文件目录不存在
                        if (!fileParent.exists()) {
                            fileParent.mkdirs();
//                            System.out.println("创建目录" + dest.getPath());
                        }
                        ExecutorUtil.UP_FILE_EXECUTOR.submit(() ->
                        {
                            try {
                                encryptionPicture(a.toString(), dev);
                            } catch (Exception e) {
//                                System.out.println(e);
                            }
                        });
                    }
                }
                else {
//                    System.out.println("NUooo");
                }
                encodeFlag =false;
//                System.out.println("目录加密完成");
            }
            if (!devFilePath.equals(originFilePath) && originFilePath != null) {
                if (!encodeFlag) {
                    encodeFlag = true;
                    encryptionPicture(originFilePath, devFilePath);
                }
                else {
                    System.out.println("NUooo");
                }
                encodeFlag =false;
                String jindu = "图片加密完成";
                process.appendText(jindu);
                process.setText(jindu);
                encodeFlag =false;
            }
    }


    public static void encryptionPicture(String orignFile, String devFile) throws IOException {
        FileInputStream fis = new FileInputStream(orignFile);
        FileOutputStream fos = new FileOutputStream(devFile);
        int s;
        while ((s = fis.read()) != -1) {
            fos.write(s ^ 123);
        }
        fis.close();
        fos.close();
    }


    private static ArrayList<Object> scanFiles = new ArrayList<Object>();

    public static ArrayList<Object> scanDest(String folderPath) {
        ArrayList<String> dirctorys = new ArrayList<String>();
        File directory = new File(folderPath);
        if (directory.isDirectory()) {
            File[] filelist = directory.listFiles();
            for (int i = 0; i < filelist.length; i++) {        /**如果当前是文件夹，进入递归扫描文件夹**/
                if (filelist[i].isDirectory()) {
                    dirctorys.add(filelist[i].getAbsolutePath());                  /**递归扫描下面的文件夹**/
                    scanDest(filelist[i].getAbsolutePath());
                }                /**非文件夹**/
                else {
                    scanFiles.add(filelist[i].getAbsoluteFile());
                }
            }
        }
        return scanFiles;
    }

}
