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

public class GroupsPageController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    ImageView bg;
    @FXML
    Label label;
    @FXML
    Button confirm;
    @FXML
    Button back;
    @FXML
    ImageView profile;
    @FXML
    Label id;
    @FXML
    ImageView group1Image;
    @FXML
    Label group1Id;
    @FXML
    Text group1Text;
    @FXML
    Button goToGroup1;
    @FXML
    ImageView group2Image;
    @FXML
    Label group2Id;
    @FXML
    Text group2Text;
    @FXML
    Button goToGroup2;
    @FXML
    ImageView group3Image;
    @FXML
    Label group3Id;
    @FXML
    Text group3Text;
    @FXML
    Button goToGroup3;
    @FXML
    ImageView group4Image;
    @FXML
    Label group4Id;
    @FXML
    Text group4Text;
    @FXML
    Button goToGroup4;
    @FXML
    TextField fillGroupId;
    @FXML
    Line line1;
    @FXML
    Line line2;
    @FXML
    Line line3;
    @FXML
    Line line4;
    @FXML
    Label setText;
    @FXML
    Label seen1;
    @FXML
    Label seen2;
    @FXML
    Label seen3;
    @FXML
    Label seen4;
    public static Group group = null;
    public static boolean toGroupPage = false;
    public ArrayList<Group> groups = new ArrayList<>();

    public void initialize() throws SQLException, FileNotFoundException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        if (manager.checkLogin().getImageAddress()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getImageAddress());
            Image image = new Image(stream);
            profile.setImage(image);
        }
        id.setText(manager.checkLogin().getId());
        int k=0;
        if (Manager.groupMessages.size()>=1) {
            group = manager.searchGroup(Manager.groupMessages.get(Manager.groupMessages.size() - 1).groupId);
            groups.add(group);
            k++;
        }
        for (int i=Manager.groupMessages.size()-2;i>=0;i--){
            group = manager.searchGroup(Manager.groupMessages.get(i).groupId);
            if (!groups.contains(group)){
                if (group.getUsers().contains(manager.checkLogin()) || group.getAdmin().equals(manager.checkLogin())) {
                    groups.add(group);
                    k++;
                }
            }
            if (k==4){
                break;
            }
        }
        if (groups.size()>=1){
            InputStream stream = new FileInputStream(groups.get(0).getImage());
            Image image = new Image(stream);
            group1Image.setImage(image);
            group1Id.setText(groups.get(0).getGroupName());
            GroupMessage groupMessage = groups.get(0).getGroupMessages().get(groups.get(0).getGroupMessages().size()-1);
            group1Text.setText(groupMessage.getSender().getId() + ": " + "\n" + groupMessage.getText());
            int num=0;
            int n=0;
            for (int i = 0; i < groups.get(0).getUsers().size(); i++) {
                if(groups.get(0).getUsers().get(i).equals(manager.checkLogin()))
                {
                    n=i;
                    break;
                }
            }
            if(groups.get(0).getAdmin().equals(manager.checkLogin()))
            {
                n=-1;
            }
            for (int i = 0; i < groups.get(0).groupMessages.size(); i++) {
                if(!groups.get(0).groupMessages.get(i).getSeen().get(n+1))
                {
                    num++;
                }
            }
            if(num>0)
            seen1.setText(Integer.toString(num));

        }
        if (groups.size()>=2){
            InputStream stream = new FileInputStream(groups.get(1).getImage());
            Image image = new Image(stream);
            group2Image.setImage(image);
            group2Id.setText(groups.get(1).getGroupName());
            GroupMessage groupMessage = groups.get(1).getGroupMessages().get(groups.get(1).getGroupMessages().size()-1);
            group2Text.setText(groupMessage.getSender().getId() + ": " + "\n" + groupMessage.getText());
            int num=0;
            int n=0;
            for (int i = 0; i < groups.get(1).getUsers().size(); i++) {
                if(groups.get(1).getUsers().get(i).equals(manager.checkLogin()))
                {
                    n=i;
                    break;
                }
            }
            if(groups.get(1).getAdmin().equals(manager.checkLogin()))
            {
                n=-1;
            }
            for (int i = 0; i < groups.get(1).groupMessages.size(); i++) {
                if(!groups.get(1).groupMessages.get(i).getSeen().get(n+1))
                {
                    num++;
                }
            }
            if(num>0)
            seen2.setText(Integer.toString(num));
        }
        if (groups.size()>=3){
            InputStream stream = new FileInputStream(groups.get(2).getImage());
            Image image = new Image(stream);
            group3Image.setImage(image);
            group3Id.setText(groups.get(2).getGroupName());
            GroupMessage groupMessage = groups.get(2).getGroupMessages().get(groups.get(2).getGroupMessages().size()-1);
            group3Text.setText(groupMessage.getSender().getId() + ": " + "\n" + groupMessage.getText());
            int num=0;
            int n=0;
            for (int i = 0; i < groups.get(2).getUsers().size(); i++) {
                if(groups.get(2).getUsers().get(i).equals(manager.checkLogin()))
                {
                    n=i;
                    break;
                }
            }
            if(groups.get(2).getAdmin().equals(manager.checkLogin()))
            {
                n=-1;
            }
            for (int i = 0; i < groups.get(2).groupMessages.size(); i++) {
                if(!groups.get(2).groupMessages.get(i).getSeen().get(n+1))
                {
                    num++;
                }
            }
            if(num>0)
            seen3.setText(Integer.toString(num));
        }
        if (groups.size()>=4){
            InputStream stream = new FileInputStream(groups.get(3).getImage());
            Image image = new Image(stream);
            group4Image.setImage(image);
            group4Id.setText(groups.get(3).getGroupName());
            GroupMessage groupMessage = groups.get(3).getGroupMessages().get(groups.get(3).getGroupMessages().size()-1);
            group4Text.setText(groupMessage.getSender().getId() + ": " + "\n" + groupMessage.getText());
            int num=0;
            int n=0;
            for (int i = 0; i < groups.get(3).getUsers().size(); i++) {
                if(groups.get(3).getUsers().get(i).equals(manager.checkLogin()))
                {
                    n=i;
                    break;
                }
            }
            if(groups.get(3).getAdmin().equals(manager.checkLogin()))
            {
                n=-1;
            }
            for (int i = 0; i < groups.get(3).groupMessages.size(); i++) {
                if(!groups.get(3).groupMessages.get(i).getSeen().get(n+1))
                {
                    num++;
                }
            }
            if(num>0)
            seen4.setText(Integer.toString(num));
        }
    }
    public void setConfirm(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        toGroupPage = true;
        CreateGroupController.toGroupPage = false;
        GroupPageController.toGroupPage=false;
        ChatPageController.toGroupPage=false;
        MainChatsPageController.toGroupPage = false;
        group = manager.searchGroup(fillGroupId.getText());
        if (group.getUsers().contains(manager.checkLogin()) || group.getAdmin().equals(manager.checkLogin())) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            setText.setText("you aren't member");
        }
    }
    public void setGoToGroup1(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        toGroupPage = true;
        CreateGroupController.toGroupPage = false;
        GroupPageController.toGroupPage=false;
        ChatPageController.toGroupPage=false;
        MainChatsPageController.toGroupPage = false;
        group = groups.get(0);
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setGoToGroup2(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        toGroupPage = true;
        CreateGroupController.toGroupPage = false;
        GroupPageController.toGroupPage=false;
        ChatPageController.toGroupPage=false;
        MainChatsPageController.toGroupPage = false;
        group = groups.get(1);
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setGoToGroup3(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        toGroupPage = true;
        CreateGroupController.toGroupPage = false;
        GroupPageController.toGroupPage=false;
        ChatPageController.toGroupPage=false;
        MainChatsPageController.toGroupPage = false;
        group = groups.get(2);
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setGoToGroup4(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        toGroupPage = true;
        CreateGroupController.toGroupPage = false;
        GroupPageController.toGroupPage=false;
        ChatPageController.toGroupPage=false;
        MainChatsPageController.toGroupPage = false;
        group = groups.get(3);
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMainPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainChatsPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
