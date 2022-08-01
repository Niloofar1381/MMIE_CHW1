package com.example.realfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class SetImagePageController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    Rectangle rectangle;
    @FXML
    Label label;
    @FXML
    ImageView image1;
    @FXML
    ImageView image2;
    @FXML
    ImageView image3;
    @FXML
    ImageView image4;
    @FXML
    CheckBox checkBox1;
    @FXML
    CheckBox checkBox2;
    @FXML
    CheckBox checkBox3;
    @FXML
    CheckBox checkBox4;
    @FXML
    Button mainPage;
    public static String str="";
    public void initialize() throws FileNotFoundException, SQLException
    {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        InputStream stream1 = new FileInputStream("C:\\Users\\ernika\\Desktop\\images\\ali.jpg");
        Image Image1 = new Image(stream1);
        image1.setImage(Image1);
        InputStream stream2 = new FileInputStream("C:\\Users\\ernika\\Desktop\\images\\mina.jpg");
        Image Image2 = new Image(stream2);
        image2.setImage(Image2);
        InputStream stream3 = new FileInputStream("C:\\Users\\ernika\\Desktop\\images\\neda.jpg");
        Image Image3 = new Image(stream3);
        image3.setImage(Image3);
        InputStream stream4 = new FileInputStream("C:\\Users\\ernika\\Desktop\\images\\reza.jpg");
        Image Image4= new Image(stream4);
        image4.setImage(Image4);
    }
    public void goToMainPage(ActionEvent event) throws IOException, SQLException
    {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (checkBox1.isSelected()) {
            str = "C:\\Users\\ernika\\Desktop\\images\\ali.jpg";
            manager.checkLogin().setImageAddress(str);
        }
        if (checkBox2.isSelected()) {
            str ="C:\\Users\\ernika\\Desktop\\images\\mina.jpg";
            manager.checkLogin().setImageAddress(str);
        }
        if (checkBox3.isSelected()) {
            str = "C:\\Users\\ernika\\Desktop\\images\\neda.jpg";
            manager.checkLogin().setImageAddress(str);
        }
        if (checkBox4.isSelected()) {
            str = "C:\\Users\\ernika\\Desktop\\images\\reza.jpg";
            manager.checkLogin().setImageAddress(str);
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
