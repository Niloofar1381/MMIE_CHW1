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
import java.time.LocalDate;
import java.util.Objects;

public class ShowProfileController {
    Manager manager=new Manager();
    Stage stage;
    Scene scene;
    @FXML
    ImageView bg;
    @FXML
    Label label;
    @FXML
    TextField profile;
    @FXML
    Button confirm;
    @FXML
    ImageView postImage1;
    @FXML
    ImageView postImage2;
    @FXML
    Button goBackToMainPage;
    @FXML
    Text postText1;
    @FXML
    Text postText2;
    @FXML
    Button follow;
    @FXML
    Label followerNumber;
    @FXML
    Label followingNumber;
    @FXML
    Label userNotFound;
    @FXML
    Label followStatus;
    @FXML
    Label id;
    @FXML
    ImageView profileImage;
    @FXML
    Button like1;
    @FXML
    Button like2;
    @FXML
    Label label1;
    @FXML
    Label label2;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        like1.setVisible(false);
        like2.setVisible(false);
    }
    public void showProfile() throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if(manager.findId(profile.getText())==null)
        {
            userNotFound.setText("User not found");
            profile.clear();
        }
        else
        {
            User user = manager.findId(profile.getText());
            id.setText(user.getId());
            if(user.getImageAddress()!=null)
            {
                InputStream stream = new FileInputStream(user.getImageAddress());
                Image image = new Image(stream);
                profileImage.setImage(image);
            }
            followerNumber.setText("follower number:" + user.getFollowerIds().size());
            followingNumber.setText("following number" + user.getFollowingIds().size());
            if (user.postIds.size()>=1) {
                InputStream stream1 = new FileInputStream(manager.searchPostById(user.postIds.get(user.postIds.size() - 1)).getImage());
                Image image1 = new Image(stream1);
                postImage1.setImage(image1);
                postText1.setText(manager.searchPostById(user.postIds.get(user.postIds.size() - 1)).getText());
                ImageView imageView1 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\likeLogo.png");
                imageView1.setFitHeight(25);
                imageView1.setFitWidth(30);
                like1.setGraphic(imageView1);
                like1.setVisible(true);
            }
            if (user.postIds.size()>=2) {
                InputStream stream2 = new FileInputStream(manager.searchPostById(user.postIds.get(user.postIds.size() - 2)).getImage());
                Image image2 = new Image(stream2);
                postImage2.setImage(image2);
                postText2.setText(manager.searchPostById(user.postIds.get(user.postIds.size() - 2)).getText());
                ImageView imageView1 = new ImageView("C:\\Users\\ernika\\Desktop\\posts\\likeLogo.png");
                imageView1.setFitHeight(25);
                imageView1.setFitWidth(30);
                like2.setGraphic(imageView1);
                like2.setVisible(true);
            }
            if (user.getBusinessAccount().equals("business")){
                if (user.postIds.size()>=1){
                    Post post1 = manager.searchPostById(user.postIds.get(user.postIds.size() - 1));
                    BusinessPost businessPost1 = (BusinessPost) post1;
                    if(!businessPost1.getViewers().contains(manager.checkLogin().getId())) {
                        businessPost1.getViewers().add(manager.checkLogin().getId());
                        businessPost1.views.put(manager.checkLogin(), LocalDate.now());
                        businessPost1.viewUsersForTable.add(manager.checkLogin().getId());
                        businessPost1.viewLocalDatesForTable.add(LocalDate.now());
                    }
                }
                if (user.postIds.size()>=2){
                    Post post2 = manager.searchPostById(user.postIds.get(user.postIds.size() - 2));
                    BusinessPost businessPost2 = (BusinessPost) post2;
                    if (!businessPost2.getViewers().contains(manager.checkLogin().getId())) {
                        businessPost2.getViewers().add(manager.checkLogin().getId());
                        businessPost2.views.put(manager.checkLogin(), LocalDate.now());
                        businessPost2.viewUsersForTable.add(manager.checkLogin().getId());
                        businessPost2.viewLocalDatesForTable.add(LocalDate.now());
                    }
                }
            }
        }
    }
    public void goToMainPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setFollow () throws SQLException
    {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        User user = manager.findId(profile.getText());
        if(user.getFollowerIds().contains(manager.checkLogin().getId()))
        {
            followStatus.setText("already followed");
        }
        else {
            user.getFollowerIds().add(manager.checkLogin().getId());
            manager.checkLogin().getFollowingIds().add(user.getId());
            followStatus.setText("followed successfully");

        }
    }
    public void setLike1() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        User user = manager.findId(profile.getText());
        Post post1 = manager.searchPostById(user.postIds.get(user.postIds.size()-1));
        String[] splitInput = new String[2];
        splitInput[0] = "LIKE";
        splitInput[1] = post1.getId();
        manager.like(splitInput);
        label1.setText("liked successfully");
    }
    public void setLike2() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        User user = manager.findId(profile.getText());
        Post post2 = manager.searchPostById(user.postIds.get(user.postIds.size()-2));
        String[] splitInput = new String[2];
        splitInput[0] = "LIKE";
        splitInput[1] = post2.getId();
        manager.like(splitInput);
        label2.setText("liked successfully");
    }
}
