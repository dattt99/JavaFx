package com.example.demo_javafx03.controller;

import com.example.demo_javafx03.entity.Department;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class DepartmentController implements Initializable{
    @FXML
    private TableColumn<Department, Integer> col01;

    @FXML
    private TableColumn<Department, String> col02;

    @FXML
    private TableView<Department> tableDepartment;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private MainController mainController;

    @FXML
    void resetAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
    }

    @FXML
    void saveAction(ActionEvent actionEvent) {
        String id = txtId.getText().trim();
        int integerId;
        if(id.isEmpty()){
            integerId = 0;
        }else {
            integerId = Integer.parseInt(id);
        }

        String name = txtName.getText().trim();
        if(name.isEmpty() || integerId == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Pls fill id and name");
            alert.setTitle("Error");
            alert.showAndWait();
        }else {
            Department department = new Department(integerId, name);
            mainController.getDepartments().add(department);
            txtId.clear();
            txtName.clear();
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        tableDepartment.setItems(mainController.getDepartments());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col01.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        col02.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
    }
}
