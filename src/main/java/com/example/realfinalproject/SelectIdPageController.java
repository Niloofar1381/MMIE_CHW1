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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class SelectIdPageController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    ImageView bg;
    @FXML
    ImageView profileImage;
    @FXML
    TextField fillId;
    @FXML
    Label label;
    @FXML
    Label id;
    @FXML
    Text messageText;
    @FXML
    Button confirm;
    @FXML
    Button back;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
    }
    public void setConfirm() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Message message = null;
        GroupMessage groupMessage = null;
        try {
            message = manager.searchMessage(Integer.parseInt(fillId.getText()));
        }
        catch (Exception e){
        groupMessage = manager.findGroupMessage(fillId.getText());
        }
        if (groupMessage!=null){
            assert false;
            messageText.setText(groupMessage.getText());
            id.setText(groupMessage.getSender().getId());
            if (groupMessage.getSender().getImageAddress()!=null){
                InputStream stream = new FileInputStream(groupMessage.getSender().getImageAddress());
                Image image = new Image(stream);
                profileImage.setImage(image);
            }
        }
        else if (message!=null){
            assert false;
            messageText.setText(message.getText());
            id.setText(message.getSender().getId());
            if (message.getSender().getImageAddress()!=null){
                InputStream stream = new FileInputStream(message.getSender().getImageAddress());
                Image image = new Image(stream);
                profileImage.setImage(image);
            }
        }
        else{
            messageText.setText("invalid id");
        }
        initialize();
    }
    public void switchToSearchTextPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchTextMessagePage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
