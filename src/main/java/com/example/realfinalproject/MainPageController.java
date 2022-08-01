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

public class MainPageController {
    Manager manager = new Manager();
    Stage stage;
    Stage stage1;
    Scene scene;
    @FXML
    Label id;
    @FXML
    Label followerNumbers;
    @FXML
    Label followingNumber;
    @FXML
    Label showId;
    @FXML
    Label showFollowerNumbers;
    @FXML
    Label showFollowingNumbers;
    @FXML
    ImageView profile;
    @FXML
    ImageView backGround;
    @FXML
    Button close;
    @FXML
    Button setProfileImage;
    @FXML
    Button setTheme;
    @FXML
    Button createPost;
    @FXML
    ImageView post1Image;
    @FXML
    ImageView post2Image;
    @FXML
    Text post1Text;
    @FXML
    Text post2Text;
    @FXML
    Button showPreviousPosts;
    @FXML
    Button logOut;
    @FXML
    Button like1;
    @FXML
    Button comment1;
    @FXML
    Button like2;
    @FXML
    Button comment2;
    @FXML
    Button showProfile;
    @FXML
    Button showFollowersPosts;
    @FXML
    Button showStats;
    @FXML
    Button createGroup;
    @FXML
    Button searchText;
    @FXML
    Button suggestFriend;
    @FXML
    Button suggestAdvertisement;
    @FXML
    Button mainChatsPage;
    @FXML
    Button settings;
    public static Post post;
    public static Post post1;
    public static Post post2;
    public static boolean mainPage=false;
    public static boolean likePost1 = false;
    public static boolean likePost2 = false;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        showStats.setVisible(false);
        comment1.setVisible(false);
        comment2.setVisible(false);
        like1.setVisible(false);
        like2.setVisible(false);
        showId.setText(manager.checkLogin().getId());
        showFollowerNumbers.setText(Integer.toString(manager.checkLogin().getFollowerIds().size()));
        showFollowingNumbers.setText(Integer.toString(manager.checkLogin().getFollowingIds().size()));
        if (manager.checkLogin().getImageAddress()!=null) {
            InputStream stream = new FileInputStream(manager.checkLogin().getImageAddress());
            Image image = new Image(stream);
            profile.setImage(image);
        }
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            backGround.setImage(image);
        }
        if (manager.checkLogin().getBusinessAccount().equals("business")){
            showStats.setVisible(true);
        }
        showRecentPosts();
    }
    public void setCreatePost(ActionEvent event) throws IOException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createPostPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showRecentPosts() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().postIds.size()>=1) {
            post1 = manager.searchPostById(manager.checkLogin().postIds.get(manager.checkLogin().postIds.size() - 1));
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
        if (manager.checkLogin().postIds.size()>=2) {
            post2 = manager.searchPostById(manager.checkLogin().postIds.get(manager.checkLogin().postIds.size() - 2));
            if (!post2.getText().equals("")) {
                post2Text.setText(post2.getText());
            }
            if (!post2.getImage().equals("")){
                InputStream stream = new FileInputStream(post2.getImage());
                Image image = new Image(stream);
                post2Image.setImage(image);
            }
            ImageView imageView1 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\likeLogo.png");
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
    }
    public void setShowPreviousPosts(ActionEvent event) throws IOException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("previousPostsPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLoginPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        manager.logout();
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

    public void goToCommentPage1(ActionEvent event) throws IOException, SQLException
    {
        mainPage=true;
        PreviousPostsController.prePostPage=false;
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
        mainPage=true;
        PreviousPostsController.prePostPage=false;
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
    public void goToLikePage1() throws SQLException, IOException {
        likePost1 = true;
        likePost2 = false;
        post = post1;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showLikeUsersPage.fxml")));
        stage1 = new Stage();
        scene = new Scene(root,600,400);
        stage1.setScene(scene);
        stage1.show();
    }
    public void goToLikePage2() throws SQLException, IOException {
        likePost2 = true;
        likePost1 = false;
        post = post2;
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showLikeUsersPage.fxml")));
        stage1 = new Stage();
        scene = new Scene(root,600,400);
        stage1.setScene(scene);
        stage1.show();
    }
    public void switchToShowProfilePage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showProfile.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToShowFriendsPostsPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("friendsPostsPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToShowStatsPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showStats.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCreateGroupPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createGroupPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    public void switchToSuggestFriendPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("suggestFriendPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSuggestBusinessPostPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("suggestBusinessPostPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMainChatsPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainChatsPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSettingsPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("settingsPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}