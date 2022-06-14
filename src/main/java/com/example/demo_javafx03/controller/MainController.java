package com.example.demo_javafx03.controller;

import com.example.demo_javafx03.Main;
import com.example.demo_javafx03.dao.DepartmentDaoImpl;
import com.example.demo_javafx03.dao.StudentDaoImpl;
import com.example.demo_javafx03.entity.Department;

import com.example.demo_javafx03.entity.Student;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private VBox xBoxRoot;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Student, String> col01;

    @FXML
    private TableColumn<Student, String> col02;

    @FXML
    private TableColumn<Student, Department> col03;

    @FXML
    private ComboBox<Department> comboDepartment;

    @FXML
    private TableView<Student> tableStudent;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLastName;


    private ObservableList<Department> departments;
    private ObservableList<Student> students;
    private Student selectedStudent;
    private DepartmentDaoImpl departmentDao;
    private StudentDaoImpl studentDao;

    @FXML
    void callDepartment(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("department-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            DepartmentController controller = fxmlLoader.getController();
            controller.setMainController(this);
            Stage mainStage = new Stage();
            mainStage.setTitle("JavaFX Main Form");
            mainStage.setScene(scene);
            mainStage.initModality(Modality.APPLICATION_MODAL);
            mainStage.initOwner(xBoxRoot.getScene().getWindow());
            mainStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {

    }

    @FXML
    void saveAction(ActionEvent event) {

    }

    @FXML
    void updateAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departmentDao = new DepartmentDaoImpl();
        studentDao = new StudentDaoImpl();

        departments = FXCollections.observableArrayList();
        try {
            departments.addAll(departmentDao.fetchAll());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        students = FXCollections.observableArrayList();
        try {
            students.addAll(studentDao.fetchAll());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        comboDepartment.setItems(departments);
        tableStudent.setItems(students);
        col01.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        col02.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName() + " " + data.getValue().getLastName()));
        col03.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDepartment()));

        tableStudent.getSelectionModel().selectedItemProperty().addListener((observableValue, student
                , t1) ->{
            selectedStudent = t1;
            if(selectedStudent !=null){
                txtId.setText(selectedStudent.getId());
                txtFirstName.setText(selectedStudent.getFirstName());
                txtLastName.setText(selectedStudent.getLastName());
                txtAddress.setText(selectedStudent.getAddress());
                comboDepartment.setValue(selectedStudent.getDepartment());
                txtId.setDisable(true);
                btnSave.setDisable(true);
                btnUpdate.setDisable(false);
            }
        });
    }

    public ObservableList<Department> getDepartments(){
        return departments;
    }
}
