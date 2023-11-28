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

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void initialize(){
            conn = DB_Connect.connect();
    }


    public void lblRegisterOnMouseClicked(MouseEvent mouseEvent) throws IOException {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/SignupForm.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) root.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Register form");
            stage.centerOnScreen();
            stage.show();
    }



    public void btnLoginOnMouseClicked(MouseEvent mouseEvent) throws IOException {

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

                try {
                    String query = "select * from operation where username=? and password=?";
                    pst = conn.prepareStatement(query);

                    pst.setString(1,username);
                    pst.setString(2,password);

                    rs = pst.executeQuery();

                    if(rs.next()){
                        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/DashboardForm.fxml"));
                        Scene scene = new Scene(parent);
                        Stage stage = (Stage) root.getScene().getWindow();

                        stage.setScene(scene);
                        stage.setTitle("Dashboard form");
                        stage.centerOnScreen();
                        stage.show();


                    }else{
                        JOptionPane.showMessageDialog(null, "Are your sure if user is registered.");
                    }



                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }else{
                JOptionPane.showMessageDialog(null,"Field's are not filled.");
            }



    }




}
