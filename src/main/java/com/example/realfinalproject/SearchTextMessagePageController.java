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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class SearchTextMessagePageController {
    Manager manager=new Manager();
    Stage stage;
    Scene scene;
    @FXML
    ImageView bg;
    @FXML
    Label label;
    @FXML
    TextField text;
    @FXML
    Button confirm;
    @FXML
    Line line;
    @FXML
    ImageView profile1;
    @FXML
    ImageView profile2;
    @FXML
    ImageView profile3;
    @FXML
    Label id1;
    @FXML
    Label id2;
    @FXML
    Label id3;
    @FXML
    Label messageId1;
    @FXML
    Label messageId2;
    @FXML
    Label messageId3;
    @FXML
    Text message1;
    @FXML
    Text message2;
    @FXML
    Text message3;
    @FXML
    Button selectId;
    @FXML
    Button back;
    @FXML
    CheckBox messageCheckBox;
    @FXML
    CheckBox groupMessageCheckBox;
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
        ArrayList<Message> messages = manager.searchTextMessage(text.getText());
        ArrayList<GroupMessage> groupMessages = manager.searchTextGroupMessage(text.getText());
        if (messageCheckBox.isSelected()) {
            if (messages.size() >= 1) {
                if (messages.get(messages.size() - 1).getSender().getImageAddress() != null) {
                    InputStream stream = new FileInputStream(messages.get(messages.size() - 1).getSender().getImageAddress());
                    Image image = new Image(stream);
                    profile1.setImage(image);
                }
                id1.setText(messages.get(messages.size() - 1).getSender().getId());
                messageId1.setText(Integer.toString(messages.get(messages.size() - 1).getId()));
                message1.setText(messages.get(messages.size() - 1).getText());
            }
            if(messages.size()>=2)
            {
                if (messages.get(messages.size() - 2).getSender().getImageAddress() != null) {
                    InputStream stream = new FileInputStream(messages.get(messages.size() - 2).getSender().getImageAddress());
                    Image image = new Image(stream);
                    profile2.setImage(image);
                }
                id2.setText(messages.get(messages.size() - 2).getSender().getId());
                messageId2.setText(Integer.toString(messages.get(messages.size() - 2).getId()));
                message2.setText(messages.get(messages.size() - 2).getText());
            }
            if(messages.size()>=3)
            {
                if (messages.get(messages.size() - 3).getSender().getImageAddress() != null) {
                    InputStream stream = new FileInputStream(messages.get(messages.size() - 3).getSender().getImageAddress());
                    Image image = new Image(stream);
                    profile3.setImage(image);
                }
                id3.setText(messages.get(messages.size() - 3).getSender().getId());
                messageId3.setText(Integer.toString(messages.get(messages.size() - 3).getId()));
                message3.setText(messages.get(messages.size() - 3).getText());
            }

        }
        if (groupMessageCheckBox.isSelected()) {
            if (groupMessages.size() >= 1) {
                if (groupMessages.get(groupMessages.size() - 1).getSender().getImageAddress() != null) {
                    InputStream stream = new FileInputStream(groupMessages.get(groupMessages.size() - 1).getSender().getImageAddress());
                    Image image = new Image(stream);
                    profile1.setImage(image);
                }
                id1.setText(groupMessages.get(groupMessages.size() - 1).getSender().getId());
                messageId1.setText(groupMessages.get(groupMessages.size() - 1).getId());
                message1.setText(groupMessages.get(groupMessages.size() - 1).getText());
            }
            if(groupMessages.size()>=2)
            {
                if (groupMessages.get(groupMessages.size() - 2).getSender().getImageAddress() != null) {
                    InputStream stream = new FileInputStream(groupMessages.get(groupMessages.size() - 2).getSender().getImageAddress());
                    Image image = new Image(stream);
                    profile2.setImage(image);
                }
                id2.setText(groupMessages.get(groupMessages.size() - 2).getSender().getId());
                messageId2.setText(groupMessages.get(groupMessages.size() - 2).getId());
                message2.setText(groupMessages.get(groupMessages.size() - 2).getText());
            }
            if(groupMessages.size()>=3)
            {
                if (groupMessages.get(groupMessages.size() - 3).getSender().getImageAddress() != null) {
                    InputStream stream = new FileInputStream(groupMessages.get(groupMessages.size() - 3).getSender().getImageAddress());
                    Image image = new Image(stream);
                    profile3.setImage(image);
                }
                id3.setText(groupMessages.get(groupMessages.size() - 3).getSender().getId());
                messageId3.setText(groupMessages.get(groupMessages.size() - 3).getId());
                message3.setText(groupMessages.get(groupMessages.size() - 3).getText());
            }

        }
        initialize();
    }
    public void switchToMainPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSelectIdPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("selectIdPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
