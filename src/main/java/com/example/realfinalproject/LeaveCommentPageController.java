package com.example.realfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class LeaveCommentPageController {
    Manager manager=new Manager();
    Stage stage;
    Scene scene;
    @FXML
    TextArea comment;
    @FXML
    Button backToMainPage;
    @FXML
    Button leaveComment;
    @FXML
    ImageView bg;
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
    }
    public void setLeaveComment(ActionEvent event) throws IOException, SQLException
    {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Post post = null;

        if(MainPageController.mainPage)
        {
            post=MainPageController.post;
        }
        if(PreviousPostsController.prePostPage)
        {
            post=PreviousPostsController.post;
        }
        if (FriendsPostsController.followerPostPage)
        {
            post = FriendsPostsController.post;
        }
        if (SuggestBusinessPostPageController.suggestBusinessPost){
            post = SuggestBusinessPostPageController.businessPost;
        }
        assert post != null;
        String postId = post.getId();
        manager.addComment(postId,comment.getText());
        manager.searchPostById(post.getCommentsId().get(post.getCommentsId().size()-1)).userId = manager.checkLogin().getId();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commentPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void goToMainPage(ActionEvent event) throws IOException, SQLException
    {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
