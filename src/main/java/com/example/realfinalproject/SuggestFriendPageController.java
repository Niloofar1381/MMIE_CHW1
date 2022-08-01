package com.example.realfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class SuggestFriendPageController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    ImageView image1;
    @FXML
    Label id1;
    @FXML
    ImageView image2;
    @FXML
    Label id2;
    @FXML
    ImageView bg;
    @FXML
    Button follow1;
    @FXML
    Button follow2;
    @FXML
    Button back;
    @FXML
    Label label;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        ArrayList<User> newFriends = manager.suggestFriend();
        if (newFriends.size()>=1){
            id1.setText(newFriends.get(0).getId());
            if (newFriends.get(0).getImageAddress()!=null) {
                InputStream stream = new FileInputStream(newFriends.get(0).getImageAddress());
                Image image = new Image(stream);
                image1.setImage(image);
            }
        }
        if (newFriends.size()>=2){
            id2.setText(newFriends.get(1).getId());
            if (newFriends.get(1).getImageAddress()!=null) {
                InputStream stream = new FileInputStream(newFriends.get(1).getImageAddress());
                Image image = new Image(stream);
                image2.setImage(image);
            }
        }
        if (newFriends.size()==0){
            label.setText("no suggestion");
        }
    }
    public void setFollow1() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        User user1 = manager.findId(id1.getText());
        user1.getFollowerIds().add(manager.checkLogin().getId());
        manager.checkLogin().getFollowingIds().add(user1.getId());
        label.setText("followed successfully");
    }
    public void setFollow2() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        User user2 = manager.findId(id2.getText());
        user2.getFollowerIds().add(manager.checkLogin().getId());
        manager.checkLogin().getFollowingIds().add(user2.getId());
        label.setText("followed successfully");
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
}
