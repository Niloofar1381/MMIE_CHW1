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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class PreviousPostsController {
    Stage stage;
    Stage stage1;
    Scene scene;
    Manager manager = new Manager();
    @FXML
    ImageView bg;
    @FXML
    ImageView post1Image;
    @FXML
    ImageView post2Image;
    @FXML
    ImageView post3Image;
    @FXML
    ImageView post4Image;
    @FXML
    Text post1Text;
    @FXML
    Text post2Text;
    @FXML
    Text post3Text;
    @FXML
    Text post4Text;
    @FXML
    Button goBackToMain;
    @FXML
    Button close;
    @FXML
    Button like1;
    @FXML
    Button like2;
    @FXML
    Button like3;
    @FXML
    Button like4;
    @FXML
    Button comment1;
    @FXML
    Button comment2;
    @FXML
    Button comment3;
    @FXML
    Button comment4;
    public  static Post post;
    public static Post post1;
    public static Post post2;
    public static Post post3;
    public static Post post4;
    public static boolean prePostPage=false;
    public static boolean likePost1 = false;
    public static boolean likePost2 = false;
    public static boolean likePost3 = false;
    public static boolean likePost4 = false;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        comment1.setVisible(false);
        comment2.setVisible(false);
        comment3.setVisible(false);
        comment4.setVisible(false);
        like1.setVisible(false);
        like2.setVisible(false);
        like3.setVisible(false);
        like4.setVisible(false);
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        showPreviousPosts();
    }
    public void showPreviousPosts() throws SQLException, FileNotFoundException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().postIds.size()>=3) {
            post1 = manager.searchPostById(manager.checkLogin().postIds.get(manager.checkLogin().postIds.size() - 3));
            if (!post1.getText().equals("")) {
                post1Text.setText(post1.getText());
            }
            if (!post1.getImage().equals("")){
                InputStream stream = new FileInputStream(post1.getImage());
                Image image = new Image(stream);
                post1Image.setImage(image);
            }
            ImageView imageView1 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\likeLogo.png");
            imageView1.setFitHeight(25);
            imageView1.setFitWidth(30);
            like1.setGraphic(imageView1);
            like1.setVisible(true);
            ImageView imageView2 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\commentLogo.png");
            imageView2.setFitHeight(25);
            imageView2.setFitWidth(30);
            comment1.setGraphic(imageView2);
            comment1.setVisible(true);
        }
        if (manager.checkLogin().postIds.size()>=4) {
            post2 = manager.searchPostById(manager.checkLogin().postIds.get(manager.checkLogin().postIds.size() - 4));
            if (!post2.getText().equals("")) {
                post2Text.setText(post2.getText());
            }
            if (!post2.getImage().equals("")){
                InputStream stream = new FileInputStream(post2.getImage());
                Image image = new Image(stream);
                post2Image.setImage(image);
            }
            ImageView imageView1= new ImageView("C:\\Users\\ernika\\Desktop\\posts\\likeLogo.png");
            imageView1.setFitHeight(25);
            imageView1.setFitWidth(30);
            like2.setGraphic(imageView1);
            like2.setVisible(true);
            ImageView imageView2 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\commentLogo.png");
            imageView2.setFitHeight(25);
            imageView2.setFitWidth(30);
            comment2.setGraphic(imageView2);
            comment2.setVisible(true);
        }
        if (manager.checkLogin().postIds.size()>=5) {
            post3 = manager.searchPostById(manager.checkLogin().postIds.get(manager.checkLogin().postIds.size() - 5));
            if (!post3.getText().equals("")) {
                post3Text.setText(post3.getText());
            }
            if (!post3.getImage().equals("")){
                InputStream stream = new FileInputStream(post3.getImage());
                Image image = new Image(stream);
                post3Image.setImage(image);
            }
            ImageView imageView1 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\likeLogo.png");
            imageView1.setFitHeight(25);
            imageView1.setFitWidth(30);
            like3.setGraphic(imageView1);
            like3.setVisible(true);
            ImageView imageView2 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\commentLogo.png");
            imageView2.setFitHeight(25);
            imageView2.setFitWidth(30);
            comment3.setGraphic(imageView2);
            comment3.setVisible(true);
        }
        if (manager.checkLogin().postIds.size()>=6) {
            post4 = manager.searchPostById(manager.checkLogin().postIds.get(manager.checkLogin().postIds.size() - 6));
            if (!post4.getText().equals("")) {
                post4Text.setText(post4.getText());
            }
            if (!post4.getImage().equals("")){
                InputStream stream = new FileInputStream(post4.getImage());
                Image image = new Image(stream);
                post4Image.setImage(image);
            }
            ImageView imageView1 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\likeLogo.png");
            imageView1.setFitHeight(25);
            imageView1.setFitWidth(30);
            like4.setGraphic(imageView1);
            like4.setVisible(true);
            ImageView imageView2= new ImageView("C:\\Users\\ernika\\Desktop\\posts\\commentLogo.png");
            imageView2.setFitHeight(25);
            imageView2.setFitWidth(30);
            comment4.setGraphic(imageView2);
            comment4.setVisible(true);
        }
    }
    public void goBackToMainPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    public void goToCommentPage1(ActionEvent event) throws IOException, SQLException
    {
        prePostPage=true;
        MainPageController.mainPage=false;
        FriendsPostsController.followerPostPage = false;
        post=post1;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commentPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToCommentPage2(ActionEvent event) throws IOException, SQLException
    {
        prePostPage=true;
        MainPageController.mainPage=false;
        FriendsPostsController.followerPostPage = false;
        post=post2;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commentPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToCommentPage3(ActionEvent event) throws IOException, SQLException
    {
        prePostPage=true;
        MainPageController.mainPage=false;
        FriendsPostsController.followerPostPage = false;
        post=post3;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commentPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToCommentPage4(ActionEvent event) throws IOException, SQLException
    {
        prePostPage=true;
        MainPageController.mainPage=false;
        FriendsPostsController.followerPostPage = false;
        post=post4;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commentPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToLike1() throws SQLException, IOException {
        likePost1 = true;
        MainPageController.likePost1 = false;
        MainPageController.likePost2 = false;
        likePost2 = false;
        likePost3 = false;
        likePost4 = false;
        post = post1;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showLikeUsersPage.fxml")));
        stage1 = new Stage();
        scene = new Scene(root,500,400);
        stage1.setScene(scene);
        stage1.show();
    }
    public void goToLike2() throws SQLException, IOException {
        likePost2 = true;
        MainPageController.likePost1 = false;
        MainPageController.likePost2 = false;
        likePost1 = false;
        likePost3 = false;
        likePost4 = false;
        post = post2;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showLikeUsersPage.fxml")));
        stage1 = new Stage();
        scene = new Scene(root,500,400);
        stage1.setScene(scene);
        stage1.show();
    }
    public void goToLike3() throws SQLException, IOException {
        likePost3 = true;
        MainPageController.likePost1 = false;
        MainPageController.likePost2 = false;
        likePost1 = false;
        likePost2 = false;
        likePost4 = false;
        post = post3;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showLikeUsersPage.fxml")));
        stage1 = new Stage();
        scene = new Scene(root,500,400);
        stage1.setScene(scene);
        stage1.show();
    }
    public void goToLike4() throws SQLException, IOException {
        likePost4 = true;
        MainPageController.likePost1 = false;
        MainPageController.likePost2 = false;
        likePost1 = false;
        likePost2 = false;
        likePost3 = false;
        post = post4;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showLikeUsersPage.fxml")));
        stage1 = new Stage();
        scene = new Scene(root,500,400);
        stage1.setScene(scene);
        stage1.show();
    }
}
