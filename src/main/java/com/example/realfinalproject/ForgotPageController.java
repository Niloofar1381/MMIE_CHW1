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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ForgotPageController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    Label id;
    @FXML
    Label nationalCode;
    @FXML
    TextField idField;
    @FXML
    TextField nationalCodeField;
    @FXML
    Button login;
    @FXML
    Button close;
    @FXML
    Label setLabel;
    @FXML
    Button back;

    public void switchToMainPage(ActionEvent event) throws IOException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        String[] splitInput = new String[4];
        splitInput[0] = "login";
        splitInput[1] = idField.getText();
        splitInput[2] = "forgot";
        splitInput[3] = nationalCodeField.getText();
        if (manager.login(splitInput).equals("logged in successfully")) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            setLabel.setText(manager.login(splitInput));
            idField.clear();
            nationalCodeField.clear();
        }
    }
    public void switchToLoginPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void closeTheStage(ActionEvent event) throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin()!=null){
            manager.logout();
        }
        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.insertAllInformation();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

}
