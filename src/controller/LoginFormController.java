package controller;

import db.DB_Connect;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    public TextField txtUser;
    public PasswordField passwordPassword;
    public Button btnLogin;
    public Label lblRegister;
    public AnchorPane root;
    public Label lblUsernameError;
    public Label lblPasswordError;



    public void lblRegisterOnMouseClicked(MouseEvent mouseEvent) throws IOException {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/SignupForm.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) root.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Register form");
            stage.centerOnScreen();
            stage.show();





    }

    public void btnLoginOnMouseClicked(MouseEvent mouseEvent) {

            String username = txtUser.getText();
            String password = passwordPassword.getText();


            int errorsCount =0;
            if(username.isEmpty()){
                lblUsernameError.setText("Username field is Empty.");
                errorsCount++;
            }

            if(password.isEmpty()){
                lblPasswordError.setText("Password field is Empty.");
                errorsCount++;
            }

            if(errorsCount==0){
                




            }else{
                JOptionPane.showMessageDialog(null,"Field's are not filled.");
            }



    }




}
