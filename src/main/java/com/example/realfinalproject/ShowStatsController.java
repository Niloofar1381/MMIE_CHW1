package com.example.realfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ShowStatsController {
    Stage stage;
    Scene scene;
    Manager manager = new Manager();
    final NumberAxis yAxis = new NumberAxis();
    final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    BarChart<String,Number> barChart;
    {
        assert false;
        barChart = new BarChart<String,Number>(xAxis,yAxis);
    }
    @FXML
    Label label;
    @FXML
    TextField postId;
    @FXML
    Button confirm;
    @FXML
    ImageView bg;
    @FXML
    Button back;
    ArrayList<LocalDate> likeLocalDate = new ArrayList<>();
    ArrayList<Integer> likeInteger = new ArrayList<>();
    ArrayList<LocalDate> viewLocalDate = new ArrayList<>();
    ArrayList<Integer> viewInteger = new ArrayList<>();
    public int getDistance(LocalDate first, LocalDate second) throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (first.getYear()==second.getYear()){
            return second.getDayOfYear() - first.getDayOfYear();
        }
        else{
            int x= first.getDayOfYear();
            int y= second.getDayOfYear();
            return 365-x+y;
        }
    }
    public void initialize() throws SQLException, IOException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        if (manager.checkLogin().getBackGround()!=null){
            InputStream stream = new FileInputStream(manager.checkLogin().getBackGround());
            Image image = new Image(stream);
            bg.setImage(image);
        }
    }
    public void likeAndView() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        BusinessPost businessPost = manager.searchBusinessPostById(postId.getText());
        int distance=getDistance(businessPost.releasedTime,LocalDate.now());
        for (int i=0;i<=distance;i++){
            int num=0,num1=0;
            for (LocalDate value : businessPost.likesLocalDateForTable) {
                if (getDistance(businessPost.releasedTime,value)==i){
                    num++;
                }
            }
            likeLocalDate.add(businessPost.releasedTime);
            likeInteger.add(num);
            for (LocalDate value : businessPost.viewLocalDatesForTable) {
                if (getDistance(businessPost.releasedTime,value)==i){
                    num1++;
                }
            }
            viewLocalDate.add(businessPost.releasedTime);
            viewInteger.add(num1);
        }
    }
    public void showChart() throws SQLException {
        SetArrayLists setArrayLists = new SetArrayLists();
        setArrayLists.setAllArrayLists();
        likeAndView();
        barChart.setTitle("views and likes per date");
        xAxis.setLabel("date");
        yAxis.setLabel("likes and views");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("likes");
        for (int i=0;i<likeLocalDate.size();i++){
            series1.getData().add(new XYChart.Data(likeLocalDate.get(i).toString(), likeInteger.get(i)));
        }
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("views");
        for (int i=0;i<viewLocalDate.size();i++){
            series2.getData().add(new XYChart.Data(viewLocalDate.get(i).toString(), viewInteger.get(i)));
        }
        barChart.getData().addAll(series1, series2);
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
