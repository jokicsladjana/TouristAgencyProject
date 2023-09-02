package com.agency.touristagency;

import com.agency.touristagency.user_interface.Controller;
import com.agency.touristagency.user_interface.paneli.LoginPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TouristAgencyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Controller.instance().setMainStage(stage);
        LoginPanel loginPanel=new LoginPanel();
        Scene scene = new Scene(loginPanel);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}