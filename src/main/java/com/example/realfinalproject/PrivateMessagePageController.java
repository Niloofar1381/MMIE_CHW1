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

public class PrivateMessagePageController {
    Manager manager=new Manager();
    Stage stage;
    Scene scene;
    @FXML
    ImageView bg;
    @FXML
    ImageView loginProfile;
    @FXML
    ImageView chatProfile1;
    @FXML
    ImageView chatProfile2;
    @FXML
    ImageView chatProfile3;
    @FXML
    ImageView chatProfile4;
    @FXML
    Label loginId;
    @FXML
    Label chatId1;
    @FXML
    Label chatId2;
    @FXML
    Label chatId3;
    @FXML
    Label chatId4;
    @FXML
    TextField id;
    @FXML
    Button confirm;
    @FXML
    Text chat1;
    @FXML
    Text chat2;
    @FXML
    Text chat3;
    @FXML
    Text chat4;
    @FXML
    Line line1;
    @FXML
    Line line2;
    @FXML
    Line line3;
    @FXML
    Line line4;
    @FXML
    Button goToChat1;
    @FXML
    Button goToChat2;
    @FXML
    Button goToChat3;
    @FXML
    Button goToChat4;
    @FXML
    Label label;
    @FXML
    Button back;
    @FXML
    Label seen1;
    @FXML
    Label seen2;
    @FXML
    Label seen3;
    @FXML
    Label seen4;
    @FXML
    Button block1;
    @FXML
    Button block2;
    @FXML
    Button block3;
    @FXML
    Button block4;
    public static User chatUser;
    public static boolean goToChatPage=false;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        ArrayList<User>usersInChat=new ArrayList<>();
        goToChat1.setVisible(false);
        goToChat2.setVisible(false);
        goToChat3.setVisible(false);
        goToChat4.setVisible(false);
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        line4.setVisible(false);
        block1.setVisible(false);
        block2.setVisible(false);
        block3.setVisible(false);
        block4.setVisible(false);
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        if(manager.checkLogin().getImageAddress()!=null)
        {
            InputStream stream = new FileInputStream(manager.checkLogin().getImageAddress());
            Image image = new Image(stream);
            loginProfile.setImage(image);
        }
        loginId.setText(manager.checkLogin().getId());
        User user=manager.checkLogin();
        int k=0;
        for (int i =Manager.messages.size()-1; i >=0; i--) {
           if(Manager.messages.get(i).getSender().equals(user)||Manager.messages.get(i).getReceiver().equals(user))
           {
              if((Manager.messages.get(i).getSender().equals(user)&&!usersInChat.contains(Manager.messages.get(i).getReceiver()))||
                      (Manager.messages.get(i).getReceiver().equals(user)&&!usersInChat.contains(Manager.messages.get(i).getSender())))
               {
                   k++;
               if(k<=4)
               {
                   if(Manager.messages.get(i).getSender().equals(user))
                   {
                       if(k==1)
                       {
                           chatId1.setText(Manager.messages.get(i).getReceiver().getId());
                           if(Manager.messages.get(i).getReceiver().getImageAddress()!=null) {
                               InputStream stream = new FileInputStream(Manager.messages.get(i).getReceiver().getImageAddress());
                               Image image = new Image(stream);
                               chatProfile1.setImage(image);
                           }
                           chat1.setText(user.getId() + ": " + Manager.messages.get(i).getText());
                           line1.setVisible(true);
                           if (user.getFollowerIds().contains(Manager.messages.get(i).getReceiver().getId())) {
                               goToChat1.setVisible(true);
                           }
                           block1.setVisible(true);
                           usersInChat.add(Manager.messages.get(i).getReceiver());

                       }
                       if(k==2)
                       {
                           chatId2.setText(Manager.messages.get(i).getReceiver().getId());
                           if (Manager.messages.get(i).getReceiver().getImageAddress()!=null) {
                               InputStream stream = new FileInputStream(Manager.messages.get(i).getReceiver().getImageAddress());
                               Image image = new Image(stream);
                               chatProfile2.setImage(image);
                           }
                           chat2.setText(user.getId()+": "+Manager.messages.get(i).getText());
                           line2.setVisible(true);
                           if(user.getFollowerIds().contains(Manager.messages.get(i).getReceiver().getId()))
                           {
                               goToChat2.setVisible(true);
                           }
                           block2.setVisible(true);
                           usersInChat.add(Manager.messages.get(i).getReceiver());

                       }
                       if(k==3)
                       {
                           chatId3.setText(Manager.messages.get(i).getReceiver().getId());
                           if (Manager.messages.get(i).getReceiver().getImageAddress()!=null) {
                               InputStream stream = new FileInputStream(Manager.messages.get(i).getReceiver().getImageAddress());
                               Image image = new Image(stream);
                               chatProfile3.setImage(image);
                           }
                           chat3.setText(user.getId()+": "+Manager.messages.get(i).getText());
                           line3.setVisible(true);
                           if(user.getFollowerIds().contains(Manager.messages.get(i).getReceiver().getId()))
                           {
                               goToChat3.setVisible(true);
                           }
                           block3.setVisible(true);
                           usersInChat.add(Manager.messages.get(i).getReceiver());
                       }
                       if(k==4)
                       {
                           chatId4.setText(Manager.messages.get(i).getReceiver().getId());
                           if (Manager.messages.get(i).getReceiver().getImageAddress()!=null) {
                               InputStream stream = new FileInputStream(Manager.messages.get(i).getReceiver().getImageAddress());
                               Image image = new Image(stream);
                               chatProfile4.setImage(image);
                           }
                           chat4.setText(user.getId()+": "+Manager.messages.get(i).getText());
                           line4.setVisible(true);
                           if(user.getFollowerIds().contains(Manager.messages.get(i).getReceiver().getId()))
                           {
                               goToChat4.setVisible(true);
                           }
                           block4.setVisible(true);
                           usersInChat.add(Manager.messages.get(i).getReceiver());
                       }
                   }
                   if(Manager.messages.get(i).getReceiver().equals(user))
                   {
                       if(k==1)
                       {
                           chatId1.setText(Manager.messages.get(i).getSender().getId());
                           if (Manager.messages.get(i).getSender().getImageAddress()!=null) {
                               InputStream stream = new FileInputStream(Manager.messages.get(i).getSender().getImageAddress());
                               Image image = new Image(stream);
                               chatProfile1.setImage(image);
                           }
                           chat1.setText(Manager.messages.get(i).getSender().getId()+": "+Manager.messages.get(i).getText());
                           line1.setVisible(true);
                           if(user.getFollowerIds().contains(Manager.messages.get(i).getSender().getId()))
                           {
                               goToChat1.setVisible(true);
                           }
                           block1.setVisible(true);
                           usersInChat.add(Manager.messages.get(i).getSender());
                           int notSeen = 0;
                           for (int i1 = 0; i1 < Manager.messages.size(); i1++) {
                               if (Manager.messages.get(i1).getReceiver().equals(user) && Manager.messages.get(i1).getSender().equals(Manager.messages.get(i).getSender())
                                && !Manager.messages.get(i1).isSeen()){
                                   notSeen++;
                               }
                           }
                           if (notSeen!=0){
                               seen1.setText(Integer.toString(notSeen));
                           }
                       }
                       if(k==2)
                       {
                           chatId2.setText(Manager.messages.get(i).getSender().getId());
                           if (Manager.messages.get(i).getSender().getImageAddress()!=null) {
                               InputStream stream = new FileInputStream(Manager.messages.get(i).getSender().getImageAddress());
                               Image image = new Image(stream);
                               chatProfile2.setImage(image);
                           }
                           chat2.setText(Manager.messages.get(i).getSender().getId()+": "+Manager.messages.get(i).getText());
                           line2.setVisible(true);
                           if(user.getFollowerIds().contains(Manager.messages.get(i).getSender().getId()))
                           {
                               goToChat2.setVisible(true);
                           }
                           block2.setVisible(true);
                           usersInChat.add(Manager.messages.get(i).getSender());
                           int notSeen = 0;
                           for (int i1 = 0; i1 < Manager.messages.size(); i1++) {
                               if (Manager.messages.get(i1).getReceiver().equals(user) && Manager.messages.get(i1).getSender().equals(Manager.messages.get(i).getSender())
                                       && !Manager.messages.get(i1).isSeen()){
                                   notSeen++;
                               }
                           }
                           if (notSeen!=0){
                               seen2.setText(Integer.toString(notSeen));
                           }

                       }
                       if(k==3)
                       {
                           chatId3.setText(Manager.messages.get(i).getSender().getId());
                           if (Manager.messages.get(i).getSender().getImageAddress()!=null) {
                               InputStream stream = new FileInputStream(Manager.messages.get(i).getSender().getImageAddress());
                               Image image = new Image(stream);
                               chatProfile3.setImage(image);
                           }
                           chat3.setText(Manager.messages.get(i).getSender().getId()+": "+Manager.messages.get(i).getText());
                           line3.setVisible(true);
                           if(user.getFollowerIds().contains(Manager.messages.get(i).getSender().getId()))
                           {
                               goToChat3.setVisible(true);
                           }
                           block3.setVisible(true);
                           usersInChat.add(Manager.messages.get(i).getSender());
                           int notSeen = 0;
                           for (int i1 = 0; i1 < Manager.messages.size(); i1++) {
                               if (Manager.messages.get(i1).getReceiver().equals(user) && Manager.messages.get(i1).getSender().equals(Manager.messages.get(i).getSender())
                                       && !Manager.messages.get(i1).isSeen()){
                                   notSeen++;
                               }
                           }
                           if (notSeen!=0){
                               seen3.setText(Integer.toString(notSeen));
                           }
                       }
                       if(k==4)
                       {
                           chatId4.setText(Manager.messages.get(i).getSender().getId());
                           if (Manager.messages.get(i).getSender().getImageAddress()!=null) {
                               InputStream stream = new FileInputStream(Manager.messages.get(i).getSender().getImageAddress());
                               Image image = new Image(stream);
                               chatProfile4.setImage(image);
                           }
                           chat4.setText(Manager.messages.get(i).getSender().getId()+": "+Manager.messages.get(i).getText());
                           line4.setVisible(true);
                           if(user.getFollowerIds().contains(Manager.messages.get(i).getSender().getId()))
                           {
                               goToChat4.setVisible(true);
                           }
                           block4.setVisible(true);
                           usersInChat.add(Manager.messages.get(i).getSender());
                           int notSeen = 0;
                           for (int i1 = 0; i1 < Manager.messages.size(); i1++) {
                               if (Manager.messages.get(i1).getReceiver().equals(user) && Manager.messages.get(i1).getSender().equals(Manager.messages.get(i).getSender())
                                       && !Manager.messages.get(i1).isSeen()){
                                   notSeen++;
                               }
                           }
                           if (notSeen!=0){
                               seen4.setText(Integer.toString(notSeen));
                           }
                       }
                   }
               }
             }
           }
        }
    }
    public void goToChatConfirm(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        goToChatPage=true;
        GroupPageController.goToChatPage=false;
        ChatPageController.goToChatPage=false;
        MainChatsPageController.goToChatPage = false;
        chatUser=manager.findId(id.getText());
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToChatButton1(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        goToChatPage=true;
        GroupPageController.goToChatPage=false;
        ChatPageController.goToChatPage=false;
        MainChatsPageController.goToChatPage = false;
        chatUser=manager.findId(chatId1.getText());
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToChatButton2(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        goToChatPage=true;
        GroupPageController.goToChatPage=false;
        ChatPageController.goToChatPage=false;
        MainChatsPageController.goToChatPage = false;
        chatUser=manager.findId(chatId2.getText());
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToChatButton3(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        goToChatPage=true;
        GroupPageController.goToChatPage=false;
        ChatPageController.goToChatPage=false;
        MainChatsPageController.goToChatPage = false;
        chatUser=manager.findId(chatId3.getText());
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToChatButton4(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        goToChatPage=true;
        GroupPageController.goToChatPage=false;
        ChatPageController.goToChatPage=false;
        MainChatsPageController.goToChatPage = false;
        chatUser=manager.findId(chatId4.getText());
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToMainPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainChatsPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setBlock1() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        manager.block(chatId1.getText());
    }
    public void setBlock2() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        manager.block(chatId2.getText());
    }
    public void setBlock3() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        manager.block(chatId3.getText());
    }
    public void setBlock4() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        manager.block(chatId4.getText());
    }

}
