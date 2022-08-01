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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class CreateGroupController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    TextField fillName;
    @FXML
    TextField fillId;
    @FXML
    Label groupName;
    @FXML
    Label groupId;
    @FXML
    Label createGroup;
    @FXML
    Label chooseProfile;
    @FXML
    Button create;
    @FXML
    Label follower1;
    @FXML
    Label follower2;
    @FXML
    Label follower3;
    @FXML
    Label follower4;
    @FXML
    Label follower5;
    @FXML
    Label follower6;
    @FXML
    Label follower7;
    @FXML
    CheckBox member1;
    @FXML
    CheckBox member2;
    @FXML
    CheckBox member3;
    @FXML
    CheckBox member4;
    @FXML
    CheckBox member5;
    @FXML
    CheckBox member6;
    @FXML
    CheckBox member7;
    @FXML
    CheckBox profile1;
    @FXML
    CheckBox profile2;
    @FXML
    CheckBox profile3;
    @FXML
    CheckBox profile4;
    @FXML
    ImageView image1;
    @FXML
    ImageView image2;
    @FXML
    ImageView image3;
    @FXML
    ImageView image4;
    @FXML
    Label label;
    @FXML
    ImageView bg;
    @FXML
    Text addedMembers;
    @FXML
    Button back;
    public int num=0;
    public static boolean toGroupPage = false;
    public static Group group=null;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        member1.setVisible(false);
        member2.setVisible(false);
        member3.setVisible(false);
        member4.setVisible(false);
        member5.setVisible(false);
        member6.setVisible(false);
        member7.setVisible(false);
        num = manager.checkLogin().getFollowerIds().size();
        if (num>7){
            member1.setVisible(true);
            member2.setVisible(true);
            member3.setVisible(true);
            member4.setVisible(true);
            member5.setVisible(true);
            member6.setVisible(true);
            member7.setVisible(true);
            follower1.setText(manager.checkLogin().getFollowerIds().get(0));
            follower2.setText(manager.checkLogin().getFollowerIds().get(1));
            follower3.setText(manager.checkLogin().getFollowerIds().get(2));
            follower4.setText(manager.checkLogin().getFollowerIds().get(3));
            follower5.setText(manager.checkLogin().getFollowerIds().get(4));
            follower6.setText(manager.checkLogin().getFollowerIds().get(5));
            follower7.setText(manager.checkLogin().getFollowerIds().get(6));
        }
        else{
            if (num>=1){
                member1.setVisible(true);
                follower1.setText(manager.checkLogin().getFollowerIds().get(0));
            }
            if (num>=2){
                member2.setVisible(true);
                follower2.setText(manager.checkLogin().getFollowerIds().get(1));
            }
            if (num>=3){
                member3.setVisible(true);
                follower3.setText(manager.checkLogin().getFollowerIds().get(2));
            }
            if (num>=4){
                member4.setVisible(true);
                follower4.setText(manager.checkLogin().getFollowerIds().get(3));
            }
            if (num>=5){
                member5.setVisible(true);
                follower5.setText(manager.checkLogin().getFollowerIds().get(4));
            }
            if (num>=6){
                member6.setVisible(true);
                follower6.setText(manager.checkLogin().getFollowerIds().get(5));
            }
            if (num==7){
                member7.setVisible(true);
                follower7.setText(manager.checkLogin().getFollowerIds().get(6));
            }
        }
        InputStream stream1 = new FileInputStream("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo1.jpg");
        Image img1 = new Image(stream1);
        image1.setImage(img1);

        InputStream stream2 = new FileInputStream("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo2.jpg");
        Image img2= new Image(stream2);
        image2.setImage(img2);

        InputStream stream3 = new FileInputStream("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo3.jpg");
        Image img3 = new Image(stream3);
        image3.setImage(img3);

        InputStream stream4 = new FileInputStream("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo4.jpg");
        Image img4 = new Image(stream4);
        image4.setImage(img4);
    }
    public void setCreateGroup(ActionEvent event) throws IOException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.searchGroup(fillId.getText())!=null){
            label.setText("choose another id...");
            fillId.clear();
        }
        else {
            manager.createGroup(fillName.getText(), fillId.getText());
            group=manager.searchGroup(fillId.getText());
            String imageAddress = "";
            if (profile1.isSelected()) {
                imageAddress = "C:\\Users\\ernika\\Desktop\\groupPhotos\\photo1.jpg";
                InputStream stream = new FileInputStream(imageAddress);
                Image image = new Image(stream);
                image1.setImage(image);
            }
            if (profile2.isSelected()) {
                imageAddress = "C:\\Users\\ernika\\Desktop\\groupPhotos\\photo2.jpg";
                InputStream stream = new FileInputStream(imageAddress);
                Image image = new Image(stream);
                image2.setImage(image);
            }
            if (profile3.isSelected()) {
                imageAddress = "C:\\Users\\ernika\\Desktop\\groupPhotos\\photo3.jpg";
                InputStream stream = new FileInputStream(imageAddress);
                Image image = new Image(stream);
                image3.setImage(image);
            }
            if (profile4.isSelected()) {
                imageAddress = "C:\\Users\\ernika\\Desktop\\groupPhotos\\photo4.jpg";
                InputStream stream = new FileInputStream(imageAddress);
                Image image = new Image(stream);
                image4.setImage(image);
            }
            if (!imageAddress.equals("")) {
                manager.searchGroup(fillId.getText()).setImage(imageAddress);
            }
            String[] splitInput = new String[4];
            splitInput[0] = "add";
            splitInput[1] = "user";
            splitInput[3] = manager.searchGroup(fillId.getText()).getGroupId();
            Group group = Manager.groups.get(Manager.groups.size()-1);
            if (member1.isSelected()){
                splitInput[2] = manager.findId(follower1.getText()).getId();
                manager.addUser(splitInput);
            }
            if (member2.isSelected()){
                splitInput[2] = manager.findId(follower2.getText()).getId();
                manager.addUser(splitInput);
            }
            if (member3.isSelected()){
                splitInput[2] = manager.findId(follower3.getText()).getId();
                manager.addUser(splitInput);
            }
            if (member4.isSelected()){
                splitInput[2] = manager.findId(follower4.getText()).getId();
                manager.addUser(splitInput);
            }
            if (member5.isSelected()){
                splitInput[2] = manager.findId(follower5.getText()).getId();
                manager.addUser(splitInput);
            }
            if (member6.isSelected()){
                splitInput[2] = manager.findId(follower6.getText()).getId();
                manager.addUser(splitInput);
            }
            if (member7.isSelected()){
                splitInput[2] = manager.findId(follower7.getText()).getId();
                manager.addUser(splitInput);
            }
            toGroupPage =true;
            GroupsPageController.toGroupPage = false;
            GroupPageController.toGroupPage=false;
            ChatPageController.toGroupPage=false;
            MainChatsPageController.toGroupPage = false;
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
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
