package com.example.realfinalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.SQLException;

public class ShowLikeUsersController {
    @FXML
    ListView likeUsers;
    @FXML
    Label label;
    public void initialize() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (MainPageController.likePost1){
            Post post1 = MainPageController.post1;
            ObservableList<String> ids= FXCollections.observableArrayList();
            ids.addAll(post1.getLikeUsersId());
            likeUsers.setItems(ids);
        }
        if (MainPageController.likePost2){
            Post post2 = MainPageController.post2;
            ObservableList<String> ids= FXCollections.observableArrayList();
            ids.addAll(post2.getLikeUsersId());
            likeUsers.setItems(ids);
        }
        if (PreviousPostsController.likePost1) {
            Post post1 = PreviousPostsController.post1;
            ObservableList<String> ids= FXCollections.observableArrayList();
            ids.addAll(post1.getLikeUsersId());
            likeUsers.setItems(ids);
        }
        if (PreviousPostsController.likePost2) {
            Post post2 = PreviousPostsController.post2;
            ObservableList<String> ids= FXCollections.observableArrayList();
            ids.addAll(post2.getLikeUsersId());
            likeUsers.setItems(ids);
        }
        if (PreviousPostsController.likePost3) {
            Post post3 = PreviousPostsController.post3;
            ObservableList<String> ids= FXCollections.observableArrayList();
            ids.addAll(post3.getLikeUsersId());
            likeUsers.setItems(ids);
        }
        if (PreviousPostsController.likePost4) {
            Post post4 = PreviousPostsController.post4;
            ObservableList<String> ids= FXCollections.observableArrayList();
            ids.addAll(post4.getLikeUsersId());
            likeUsers.setItems(ids);
        }
    }
}
