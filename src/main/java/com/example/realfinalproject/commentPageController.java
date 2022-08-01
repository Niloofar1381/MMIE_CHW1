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
import java.util.Objects;

public class commentPageController {
    Manager manager=new Manager();
    Stage stage;
    Scene scene;
    @FXML
    ImageView bg;
    @FXML
    ImageView comment1Image;
    @FXML
    ImageView comment2Image;
    @FXML
    ImageView comment3Image;
    @FXML
    ImageView comment4Image;
    @FXML
    Label comment1;
    @FXML
    Label comment2;
    @FXML
    Label comment3;
    @FXML
    Label comment4;
    @FXML
    Label id1;
    @FXML
    Label id2;
    @FXML
    Label id3;
    @FXML
    Label id4;
    @FXML
    Button back;
    @FXML
    Button goToLeaveCommentPage;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        Post post = null;
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        if(PreviousPostsController.prePostPage)
        {
            post=PreviousPostsController.post;
        }
        if (FriendsPostsController.followerPostPage)
        {
            post = FriendsPostsController.post;
        }
        if (MainPageController.mainPage)
        {
            post=MainPageController.post;
        }
        assert post != null;
        if(post.commentsId.size()>=1)
        {
            comment1.setText(manager.searchPostById(post.commentsId.get(post.commentsId.size()-1)).getText());
            if(manager.findId(manager.searchPostById(post.commentsId.get(post.commentsId.size()-1)).userId).getImageAddress()!=null)
            {
                InputStream stream = new FileInputStream(manager.findId(manager.searchPostById(post.commentsId.get(post.commentsId.size()-1)).userId).getImageAddress());
                Image image = new Image(stream);
                comment1Image.setImage(image);
            }
            id1.setText(manager.searchPostById(post.commentsId.get(post.commentsId.size()-1)).userId);
        }
        if(post.commentsId.size()>=2)
        {
            comment2.setText(manager.searchPostById(post.commentsId.get(post.commentsId.size()-2)).getText());
            if(manager.findId(manager.searchPostById(post.commentsId.get(post.commentsId.size()-2)).userId).getImageAddress()!=null) {
                InputStream stream = new FileInputStream(manager.findId(manager.searchPostById(post.commentsId.get(post.commentsId.size() - 2)).userId).getImageAddress());
                Image image = new Image(stream);
                comment2Image.setImage(image);
            }
            id2.setText(manager.searchPostById(post.commentsId.get(post.commentsId.size()-2)).userId);
        }
        if(post.commentsId.size()>=3) {
            comment3.setText(manager.searchPostById(post.commentsId.get(post.commentsId.size()-3)).getText());
            if(manager.findId(manager.searchPostById(post.commentsId.get(post.commentsId.size()-3)).userId).getImageAddress()!=null) {
                InputStream stream = new FileInputStream(manager.findId(manager.searchPostById(post.commentsId.get(post.commentsId.size() - 3)).userId).getImageAddress());
                Image image = new Image(stream);
                comment3Image.setImage(image);
            }
            id3.setText(manager.searchPostById(post.commentsId.get(post.commentsId.size()-3)).userId);
        }
        if(post.commentsId.size()>=4)
        {
            comment4.setText(manager.searchPostById(post.commentsId.get(post.commentsId.size()-4)).getText());
            if(manager.findId(manager.searchPostById(post.commentsId.get(post.commentsId.size()-4)).userId).getImageAddress()!=null) {
                InputStream stream = new FileInputStream(manager.findId(manager.searchPostById(post.commentsId.get(post.commentsId.size() - 4)).userId).getImageAddress());
                Image image = new Image(stream);
                comment4Image.setImage(image);
            }
            id4.setText(manager.searchPostById(post.commentsId.get(post.commentsId.size()-4)).userId);
        }


    }
    public void switchToLeaveCommentPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("leaveCommentPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root = null;
        if (PreviousPostsController.prePostPage){
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("previousPostsPage.fxml")));
        }
        else if (MainPageController.mainPage){
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
        }
        else if (FriendsPostsController.followerPostPage){
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("friendsPostsPage.fxml")));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        assert root != null;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
