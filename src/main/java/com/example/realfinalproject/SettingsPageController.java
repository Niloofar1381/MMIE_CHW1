package com.example.realfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SettingsPageController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    Button changeTheme;
    @FXML
    Button setProfileImage;
    @FXML
    Button changePassword;
    @FXML
    Button changeId;
    @FXML
    Rectangle rectangle;
    @FXML
    TextField fillChangeId;
    @FXML
    TextField fillChangePassword;
    @FXML
    Label labelId;
    @FXML
    Label labelPassword;
    @FXML
    Button back;
    public void setImage(ActionEvent event) throws IOException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("setImagePage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setBackGround(ActionEvent event) throws IOException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("themePage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setChangePassword() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        String newPassword = fillChangePassword.getText();
        manager.checkLogin().setPassword(newPassword);
        labelPassword.setText("changed successfully");
    }
    public void setChangeId() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        String newId = fillChangeId.getText();
        if (manager.findId(newId)!=null){
            labelId.setText("invalid id");
        }
        else{
            manager.checkLogin().setId(newId);
            labelId.setText("changed successfully");
        }
    }
    public void switchToMainPage(ActionEvent event) throws IOException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
