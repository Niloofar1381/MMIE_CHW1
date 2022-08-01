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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MainChatsPageController {
    Manager manager = new Manager();
    Stage stage;
    Scene scene;
    @FXML
    Button privateMessage;
    @FXML
    Button groups;
    @FXML
    ImageView bg;
    @FXML
    Button back;
    @FXML
    Label label;
    @FXML
    Line line1;
    @FXML
    Line line2;
    @FXML
    Line line3;
    @FXML
    Line line4;
    @FXML
    ImageView image1;
    @FXML
    Label id1;
    @FXML
    Text text1;
    @FXML
    Label seen1;
    @FXML
    Button goToChat1;
    @FXML
    ImageView image2;
    @FXML
    Label id2;
    @FXML
    Text text2;
    @FXML
    Label seen2;
    @FXML
    Button goToChat2;
    @FXML
    ImageView image3;
    @FXML
    Label id3;
    @FXML
    Text text3;
    @FXML
    Label seen3;
    @FXML
    Button goToChat3;
    @FXML
    ImageView image4;
    @FXML
    Label id4;
    @FXML
    Text text4;
    @FXML
    Label seen4;
    @FXML
    Button goToChat4;
    public static boolean findMessage1 = false,findMessage2 = false,findMessage3 = false,findMessage4 = false;
    public static boolean findGroupMessage1 = false,findGroupMessage2 = false,findGroupMessage3 = false,findGroupMessage4 = false;
    public static boolean goToChatPage = false,toGroupPage = false;
    public static User chatUser = null;
    public static Group group = null;
    public void initialize() throws FileNotFoundException, SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Group> groups = new ArrayList<>();
        if (manager.checkLogin().getBackGround()!=null) {
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
        ArrayList<LocalDateTime> times = new ArrayList<>();
        for (int i=0;i<Manager.messages.size();i++){
            String str = Manager.messages.get(i).getTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            times.add(dateTime);
        }
        for (int i=0;i<Manager.groupMessages.size();i++){
            String str = Manager.groupMessages.get(i).getTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            times.add(dateTime);
        }
        Collections.sort(times);
        int k=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime time1 = null;
        LocalDateTime time2 = null;
        LocalDateTime time3 = null;
        LocalDateTime time4 = null;
        for (int i=times.size()-1;i>=0;i--){
            Group group = null;
            if (manager.findMessageByTime(dtf.format(times.get(i)))!=null){
                Message message = manager.findMessageByTime(dtf.format(times.get(i)));
                User user = null;
                if (message.getSender().equals(manager.checkLogin())){
                    user = message.getReceiver();
                }
                else if (message.getReceiver().equals(manager.checkLogin())){
                    user = message.getSender();
                }
                if (!users.contains(user) && (message.getSender().equals(manager.checkLogin()) || message.getReceiver().equals(manager.checkLogin()))){
                    users.add(user);
                    k++;
                    if (k==1){
                        time1 = times.get(i);
                    }
                    if (k==2){
                        time2 = times.get(i);
                    }
                    if (k==3){
                        time3 = times.get(i);
                    }
                    if (k==4){
                        time4 = times.get(i);
                    }
                }
            }
            if (manager.findGroupMessageByTime(dtf.format(times.get(i)))!=null){
                GroupMessage groupMessage = manager.findGroupMessageByTime(dtf.format(times.get(i)));
                group = manager.searchGroup(groupMessage.groupId);
                if (!groups.contains(group) && (group.getUsers().contains(manager.checkLogin()) || group.getAdmin().equals(manager.checkLogin()))){
                    groups.add(group);
                    k++;
                    if (k==1){
                        time1 = times.get(i);
                    }
                    if (k==2){
                        time2 = times.get(i);
                    }
                    if (k==3){
                        time3 = times.get(i);
                    }
                    if (k==4){
                        time4 = times.get(i);
                    }
                }
            }
        }
        if (time1!=null) {
            if (manager.findMessageByTime(dtf.format(time1))!=null){
                findMessage1 = true;
                Message message1 = manager.findMessageByTime(dtf.format(time1));
                if (message1.getSender().getImageAddress()!=null){
                    InputStream stream = new FileInputStream(message1.getSender().getImageAddress());
                    Image image = new Image(stream);
                    image1.setImage(image);
                }
                id1.setText(message1.getSender().getId());
                text1.setText(message1.getText());
                int notSeen1 = 0;
                for (int i1 = 0; i1 < Manager.messages.size(); i1++) {
                    if (Manager.messages.get(i1).getReceiver().equals(manager.checkLogin()) && Manager.messages.get(i1).getSender().equals(message1.getSender())
                            && !Manager.messages.get(i1).isSeen()){
                        notSeen1++;
                    }
                }
                if (notSeen1!=0){
                    seen1.setText(Integer.toString(notSeen1));
                }
            }
            if (manager.findGroupMessageByTime(dtf.format(time1))!=null){
                GroupMessage groupMessage1 = manager.findGroupMessageByTime(dtf.format(time1));
                findGroupMessage1 = true;
                if (manager.searchGroup(groupMessage1.groupId).getImage()!=null){
                    InputStream stream = new FileInputStream(manager.searchGroup(groupMessage1.groupId).getImage());
                    Image image = new Image(stream);
                    image1.setImage(image);
                }
                id1.setText(manager.searchGroup(groupMessage1.groupId).getGroupId());
                text1.setText(groupMessage1.getText());
                int num=0;
                int n=0;
                Group group = manager.searchGroup(groupMessage1.groupId);
                for (int i = 0; i < group.getUsers().size(); i++) {
                    if(group.getUsers().get(i).equals(manager.checkLogin()))
                    {
                        n=i;
                        break;
                    }
                }
                if(group.getAdmin().equals(manager.checkLogin()))
                {
                    n=-1;
                }
                for (int i = 0; i < group.groupMessages.size(); i++) {
                    if(!group.groupMessages.get(i).getSeen().get(n+1))
                    {
                        num++;
                    }
                }
                if(num>0)
                    seen1.setText(Integer.toString(num));
            }
        }
        if (time2!=null) {
            if (manager.findMessageByTime(dtf.format(time2))!=null){
                findMessage2 = true;
                Message message2 = manager.findMessageByTime(dtf.format(time2));
                if (message2.getSender().getImageAddress()!=null){
                    InputStream stream = new FileInputStream(message2.getSender().getImageAddress());
                    Image image = new Image(stream);
                    image2.setImage(image);
                }
                id2.setText(message2.getSender().getId());
                text2.setText(message2.getText());
                int notSeen2 = 0;
                for (int i1 = 0; i1 < Manager.messages.size(); i1++) {
                    if (Manager.messages.get(i1).getReceiver().equals(manager.checkLogin()) && Manager.messages.get(i1).getSender().equals(message2.getSender())
                            && !Manager.messages.get(i1).isSeen()){
                        notSeen2++;
                    }
                }
                if (notSeen2!=0){
                    seen2.setText(Integer.toString(notSeen2));
                }
            }
            if (manager.findGroupMessageByTime(dtf.format(time2))!=null){
                GroupMessage groupMessage2 = manager.findGroupMessageByTime(dtf.format(time2));
                findGroupMessage2 = true;
                if (manager.searchGroup(groupMessage2.groupId).getImage()!=null){
                    InputStream stream = new FileInputStream(manager.searchGroup(groupMessage2.groupId).getImage());
                    Image image = new Image(stream);
                    image2.setImage(image);
                }
                id2.setText(manager.searchGroup(groupMessage2.groupId).getGroupId());
                text2.setText(groupMessage2.getText());
                int num=0;
                int n=0;
                Group group = manager.searchGroup(groupMessage2.groupId);
                for (int i = 0; i < group.getUsers().size(); i++) {
                    if(group.getUsers().get(i).equals(manager.checkLogin()))
                    {
                        n=i;
                        break;
                    }
                }
                if(group.getAdmin().equals(manager.checkLogin()))
                {
                    n=-1;
                }
                for (int i = 0; i < group.groupMessages.size(); i++) {
                    if(!group.groupMessages.get(i).getSeen().get(n+1))
                    {
                        num++;
                    }
                }
                if(num>0)
                    seen2.setText(Integer.toString(num));
            }
        }
        if (time3!=null) {
            if (manager.findMessageByTime(dtf.format(time3))!=null){
                findMessage3 = true;
                Message message3 = manager.findMessageByTime(dtf.format(time3));
                if (message3.getSender().getImageAddress()!=null){
                    InputStream stream = new FileInputStream(message3.getSender().getImageAddress());
                    Image image = new Image(stream);
                    image3.setImage(image);
                }
                id3.setText(message3.getSender().getId());
                text3.setText(message3.getText());
                int notSeen3 = 0;
                for (int i1 = 0; i1 < Manager.messages.size(); i1++) {
                    if (Manager.messages.get(i1).getReceiver().equals(manager.checkLogin()) && Manager.messages.get(i1).getSender().equals(message3.getSender())
                            && !Manager.messages.get(i1).isSeen()){
                        notSeen3++;
                    }
                }
                if (notSeen3!=0){
                    seen3.setText(Integer.toString(notSeen3));
                }
            }
            if (manager.findGroupMessageByTime(dtf.format(time3))!=null){
                findGroupMessage3 = true;
                GroupMessage groupMessage3 = manager.findGroupMessageByTime(dtf.format(time3));
                if (manager.searchGroup(groupMessage3.groupId).getImage()!=null){
                    InputStream stream = new FileInputStream(manager.searchGroup(groupMessage3.groupId).getImage());
                    Image image = new Image(stream);
                    image3.setImage(image);
                }
                id3.setText(manager.searchGroup(groupMessage3.groupId).getGroupId());
                text3.setText(groupMessage3.getText());
                int num=0;
                int n=0;
                Group group = manager.searchGroup(groupMessage3.groupId);
                for (int i = 0; i < group.getUsers().size(); i++) {
                    if(group.getUsers().get(i).equals(manager.checkLogin()))
                    {
                        n=i;
                        break;
                    }
                }
                if(group.getAdmin().equals(manager.checkLogin()))
                {
                    n=-1;
                }
                for (int i = 0; i < group.groupMessages.size(); i++) {
                    if(!group.groupMessages.get(i).getSeen().get(n+1))
                    {
                        num++;
                    }
                }
                if(num>0)
                    seen3.setText(Integer.toString(num));
            }
        }
        if (time4!=null) {
            if (manager.findMessageByTime(dtf.format(time4))!=null){
                findMessage4 = true;
                Message message4 = manager.findMessageByTime(dtf.format(time4));
                if (message4.getSender().getImageAddress()!=null){
                    InputStream stream = new FileInputStream(message4.getSender().getImageAddress());
                    Image image = new Image(stream);
                    image4.setImage(image);
                }
                id4.setText(message4.getSender().getId());
                text4.setText(message4.getText());
                int notSeen4 = 0;
                for (int i1 = 0; i1 < Manager.messages.size(); i1++) {
                    if (Manager.messages.get(i1).getReceiver().equals(manager.checkLogin()) && Manager.messages.get(i1).getSender().equals(message4.getSender())
                            && !Manager.messages.get(i1).isSeen()){
                        notSeen4++;
                    }
                }
                if (notSeen4!=0){
                    seen4.setText(Integer.toString(notSeen4));
                }
            }
            if (manager.findGroupMessageByTime(dtf.format(time4))!=null){
                findGroupMessage4 = true;
                GroupMessage groupMessage4 = manager.findGroupMessageByTime(dtf.format(time4));
                if (manager.searchGroup(groupMessage4.groupId).getImage()!=null){
                    InputStream stream = new FileInputStream(manager.searchGroup(groupMessage4.groupId).getImage());
                    Image image = new Image(stream);
                    image4.setImage(image);
                }
                id4.setText(manager.searchGroup(groupMessage4.groupId).getGroupId());
                text4.setText(groupMessage4.getText());
                int num=0;
                int n=0;
                Group group = manager.searchGroup(groupMessage4.groupId);
                for (int i = 0; i < group.getUsers().size(); i++) {
                    if(group.getUsers().get(i).equals(manager.checkLogin()))
                    {
                        n=i;
                        break;
                    }
                }
                if(group.getAdmin().equals(manager.checkLogin()))
                {
                    n=-1;
                }
                for (int i = 0; i < group.groupMessages.size(); i++) {
                    if(!group.groupMessages.get(i).getSeen().get(n+1))
                    {
                        num++;
                    }
                }
                if(num>0)
                    seen4.setText(Integer.toString(num));
            }
        }
    }
    public void setGoToChat1(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root = null;
        if (findMessage1) {
            goToChatPage = true;
            PrivateMessagePageController.goToChatPage = false;
            ChatPageController.goToChatPage = false;
            GroupPageController.goToChatPage = false;
            chatUser = manager.findId(id1.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        }
        if (findGroupMessage1){
            toGroupPage = true;
            GroupPageController.toGroupPage = false;
            GroupsPageController.toGroupPage = false;
            ChatPageController.toGroupPage = false;
            CreateGroupController.toGroupPage = false;
            group = manager.searchGroup(id1.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        assert root != null;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setGoToChat2(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root = null;
        if (findMessage2) {
            goToChatPage = true;
            PrivateMessagePageController.goToChatPage = false;
            ChatPageController.goToChatPage = false;
            GroupPageController.goToChatPage = false;
            chatUser = manager.findId(id2.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        }
        if (findGroupMessage2){
            toGroupPage = true;
            GroupPageController.toGroupPage = false;
            GroupsPageController.toGroupPage = false;
            ChatPageController.toGroupPage = false;
            CreateGroupController.toGroupPage = false;
            group = manager.searchGroup(id2.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        assert root != null;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setGoToChat3(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root = null;
        if (findMessage3) {
            goToChatPage = true;
            PrivateMessagePageController.goToChatPage = false;
            ChatPageController.goToChatPage = false;
            GroupPageController.goToChatPage = false;
            chatUser = manager.findId(id3.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        }
        if (findGroupMessage3){
            toGroupPage = true;
            GroupPageController.toGroupPage = false;
            GroupsPageController.toGroupPage = false;
            ChatPageController.toGroupPage = false;
            CreateGroupController.toGroupPage = false;
            group = manager.searchGroup(id3.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        assert root != null;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setGoToChat4(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root = null;
        if (findMessage4) {
            goToChatPage = true;
            PrivateMessagePageController.goToChatPage = false;
            ChatPageController.goToChatPage = false;
            GroupPageController.goToChatPage = false;
            chatUser = manager.findId(id4.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatPage.fxml")));
        }
        if (findGroupMessage4){
            toGroupPage = true;
            GroupPageController.toGroupPage = false;
            GroupsPageController.toGroupPage = false;
            ChatPageController.toGroupPage = false;
            CreateGroupController.toGroupPage = false;
            group = manager.searchGroup(id4.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupPage.fxml")));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        assert root != null;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGroupsPage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("groupsPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPrivateMessagePage(ActionEvent event) throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("privateMessagePage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
