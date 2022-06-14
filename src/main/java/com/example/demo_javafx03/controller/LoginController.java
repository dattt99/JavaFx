package com.example.demo_javafx03.controller;

import com.example.demo_javafx03.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField txtEmail;

    @FXML
    public TextField txtPassword;

    @FXML
    public VBox rootGrid;

    public void loginAction() {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        if(email.equals("dattt@gmail.com") && password.equals("12345")){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage mainStage = new Stage();
                mainStage.setTitle("JavaFX Main Form");
                mainStage.setScene(scene);
                mainStage.show();

                ((Stage) rootGrid.getScene().getWindow()).close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Login gagal");
            alert.setTitle("Login info");
            alert.showAndWait();
        }
    }
}
