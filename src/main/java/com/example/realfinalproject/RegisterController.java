package com.example.realfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class RegisterController {
    Stage stage;
    Scene scene;
    Manager manager = new Manager();
    @FXML
    Label id;
    @FXML
    Label password;
    @FXML
    Label repeatPassword;
    @FXML
    Label nationalCode;
    @FXML
    Label userType;
    @FXML
    TextField idField;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField repeatPasswordField;
    @FXML
    TextField nationalCodeField;
    @FXML
    CheckBox ordinary;
    @FXML
    CheckBox business;
    @FXML
    Button register;
    @FXML
    Rectangle rectangle;
    @FXML
    Button close;
    @FXML
    Label setString;
    @FXML
    Button back;
    boolean goToNextPage=true;
    String[] splitInput = new String[6];
    public void setRegister(ActionEvent event) throws IOException, SQLException
    {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        splitInput[0] = "register";
        splitInput[1] = idField.getText();
        splitInput[2] = passwordField.getText();
        splitInput[3] = repeatPasswordField.getText();
        splitInput[4] = nationalCodeField.getText();
        if (ordinary.isSelected()){
            splitInput[5] = "ordinary";
        }
        else if (business.isSelected()){
            splitInput[5] = "business";
        }
        setLabel();
        manager.register(splitInput);
        if (goToNextPage) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginPage.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void setLabel() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        goToNextPage = true;
        if (manager.searchIds(splitInput[1]))
        {
            goToNextPage = false;
            setString.setText("this id has been selected choose another id...");
            idField.clear();
            passwordField.clear();
            repeatPasswordField.clear();
            nationalCodeField.clear();
            ordinary.setSelected(false);
            business.setSelected(false);
        }
        else{
            if (splitInput[2].length()<8){
                goToNextPage = false;
                setString.setText("choose another password...");
                idField.clear();
                passwordField.clear();
                repeatPasswordField.clear();
                nationalCodeField.clear();
                ordinary.setSelected(false);
                business.setSelected(false);
            }
            else{
                if (!splitInput[3].equals(splitInput[2])){
                    goToNextPage = false;
                    setString.setText("password is not valid...");
                    idField.clear();
                    passwordField.clear();
                    repeatPasswordField.clear();
                    nationalCodeField.clear();
                    ordinary.setSelected(false);
                    business.setSelected(false);
                }
            }
        }
    }
    public void switchToRegisterOrLoginPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginOrRegister.fxml")));
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
