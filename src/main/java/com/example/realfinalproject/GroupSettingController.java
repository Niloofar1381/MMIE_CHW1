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

public class GroupSettingController {
    Stage stage;
    Scene scene;
    Manager manager = new Manager();
    @FXML
    ImageView bg;
    @FXML
    TextField fillChangeGroupName;
    @FXML
    TextField fillChangeGroupId;
    @FXML
    TextField fillBanUser;
    @FXML
    TextField fillRemoveUser;
    @FXML
    Button changeGroupName;
    @FXML
    Button changeGroupId;
    @FXML
    Button banUser;
    @FXML
    Button removeUser;
    @FXML
    Label changeProfile;
    @FXML
    CheckBox setProfile1Image;
    @FXML
    CheckBox setProfile2Image;
    @FXML
    CheckBox setProfile3Image;
    @FXML
    CheckBox setProfile4Image;
    @FXML
    ImageView profile1Image;
    @FXML
    ImageView profile2Image;
    @FXML
    ImageView profile3Image;
    @FXML
    ImageView profile4Image;
    @FXML
    Button back;
    @FXML
    Button changeImage;
    @FXML
    Text labelChangeName;
    @FXML
    Text labelChangeId;
    @FXML
    Text labelBanUser;
    @FXML
    Text labelRemoveUser;
    @FXML
    TextField memberId;
    @FXML
    Button addMember;
    @FXML
    Label label;
    public void initialize() throws FileNotFoundException {
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        InputStream stream1 = new FileInputStream("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo1.jpg");
        Image img1 = new Image(stream1);
        profile1Image.setImage(img1);

        InputStream stream2 = new FileInputStream("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo2.jpg");
        Image img2= new Image(stream2);
        profile2Image.setImage(img2);

        InputStream stream3 = new FileInputStream("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo3.jpg");
        Image img3 = new Image(stream3);
        profile3Image.setImage(img3);

        InputStream stream4 = new FileInputStream("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo4.jpg");
        Image img4 = new Image(stream4);
        profile4Image.setImage(img4);
    }
    public void setAddMember() throws  SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Group group=GroupPageController.group;
        User user = manager.findId(memberId.getText());
        if (user!=null) {
            if (manager.checkLogin().getFollowerIds().contains(user.getId())) {
                group.getUsers().add(user);
                group.getBanned().add(false);
                for (int i = 0; i < group.groupMessages.size(); i++) {
                    group.groupMessages.get(i).getSeen().add(false);
                }
                label.setText("added successfully");
            } else {
                label.setText("he isn't your follower...");
                memberId.clear();
            }
        }
        memberId.clear();
    }
    public void setChangeGroupName(){
        String[] splitInput = new String[4];
        splitInput[0] = "change";
        splitInput[1] = "groupName";
        splitInput[2] = GroupPageController.group.getGroupId();
        splitInput[3] = fillChangeGroupName.getText();
        labelChangeName.setText(manager.changeGroupName(splitInput));
        fillChangeGroupName.clear();
    }
    public void setChangeGroupId(){
        String[] splitInput = new String[4];
        splitInput[0] = "change";
        splitInput[1] = "groupId";
        splitInput[2] = GroupPageController.group.getGroupId();
        splitInput[3] = fillChangeGroupId.getText();
        labelChangeId.setText(manager.changeGroupId(splitInput));
        fillChangeGroupId.clear();
    }
    public void setBanUser(){
        String[] splitInput = new String[4];
        splitInput[0] = "ban";
        splitInput[1] = "user";
        splitInput[2] = GroupPageController.group.getGroupId();
        splitInput[3] = fillBanUser.getText();
        labelBanUser.setText(manager.banUser(splitInput));
        fillBanUser.clear();
    }
    public void setRemoveUser(){
        String[] splitInput = new String[4];
        splitInput[0] = "remove";
        splitInput[1] = "user";
        splitInput[2] = GroupPageController.group.getGroupId();
        splitInput[3] = fillRemoveUser.getText();
        labelRemoveUser.setText(manager.removeUser(splitInput));
        fillRemoveUser.clear();
    }
    public void setChangeImage(){
        Group group = GroupPageController.group;
        if (setProfile1Image.isSelected()){
            group.setImage("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo1.jpg");
        }
        if (setProfile2Image.isSelected()){
            group.setImage("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo2.jpg");
        }
        if (setProfile3Image.isSelected()){
            group.setImage("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo3.jpg");
        }
        if (setProfile4Image.isSelected()){
            group.setImage("C:\\Users\\ernika\\Desktop\\groupPhotos\\photo4.jpg");
        }
    }
    public void switchToGroupPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}