package controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;


public class DashboardFormController {
    public AnchorPane root;
    public ListView<String> listViewSet;
    public TextField txtFieldTaskName;
    public Button btnLogout;
    public Button btnAddToList;

    public Label lblTaskNameError;
    public TextField changeTaskField;
    public Button btnDelete;
    public Button btnUpdate;


    public void initialize(){
        listViewSet.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                    changeTaskField.setText(newValue);
            }
        });
    }



    public void btnLogoutOnMouseClicked(MouseEvent mouseEvent) throws IOException {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login form");
            stage.centerOnScreen();
            stage.show();



    }

    public void btnAddToListOnMouseClicked(MouseEvent mouseEvent) {

            String taskName = txtFieldTaskName.getText();



            if(taskName.isEmpty()){
                        lblTaskNameError.setText("Task Name field is empty.");

            }else{
                        listViewSet.getItems().add(taskName);
                        txtFieldTaskName.setText("");
            }


    }


    public void btnUpdateOnMouseClicked(MouseEvent mouseEvent) {
            String updatedTask = changeTaskField.getText();

            int selectedId = listViewSet.getSelectionModel().getSelectedIndex();
            listViewSet.getItems().remove(selectedId);
            listViewSet.getItems().add(updatedTask);
            changeTaskField.setText("");
    }

    public void btnDeleteOnMouseClicked(MouseEvent mouseEvent) {
            int selectedId = listViewSet.getSelectionModel().getSelectedIndex();
            listViewSet.getItems().remove(selectedId);
    }
}
