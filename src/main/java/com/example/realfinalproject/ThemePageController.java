package com.example.realfinalproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class ThemePageController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    ImageView back1;
    @FXML
    ImageView back2;
    @FXML
    ImageView back3;
    @FXML
    ImageView back4;
    @FXML
    Button mainPage;
    @FXML
    CheckBox setBg1;
    @FXML
    CheckBox setBg2;
    @FXML
    CheckBox setBg3;
    @FXML
    CheckBox setBg4;
    public static String str="";
    public void initialize() throws FileNotFoundException, SQLException
    {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        InputStream stream1 = new FileInputStream("C:\\Users\\ernika\\Desktop\\background\\back1.jpg");
        Image Image1 = new Image(stream1);
        back1.setImage(Image1);
        InputStream stream2 = new FileInputStream("C:\\Users\\ernika\\Desktop\\background\\back2.jpg");
        Image Image2 = new Image(stream2);
        back2.setImage(Image2);
        InputStream stream3 = new FileInputStream("C:\\Users\\ernika\\Desktop\\background\\back3.jpg");
        Image Image3 = new Image(stream3);
        back3.setImage(Image3);
        InputStream stream4 = new FileInputStream("C:\\Users\\ernika\\Desktop\\background\\back4.jpg");
        Image Image4= new Image(stream4);
        back4.setImage(Image4);
    }
    public void goToMainPage(ActionEvent event) throws IOException, SQLException
    {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (setBg1.isSelected()) {
            str = "C:\\Users\\ernika\\Desktop\\background\\back1.jpg";
            manager.checkLogin().setBackGround(str);
        }
        if (setBg2.isSelected()) {
            str ="C:\\Users\\ernika\\Desktop\\background\\back2.jpg" ;
            manager.checkLogin().setBackGround(str);
        }
        if (setBg3.isSelected()) {
            str = "C:\\Users\\ernika\\Desktop\\background\\back3.jpg";
            manager.checkLogin().setBackGround(str);
        }
        if (setBg4.isSelected()) {
            str = "C:\\Users\\ernika\\Desktop\\background\\back4.jpg";
            manager.checkLogin().setBackGround(str);
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
