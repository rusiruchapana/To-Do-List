package controller;

import db.DB_Connect;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public Button btnRgstr;
    public TextField fldUserName;
    public PasswordField fldPassword;
    public TextField fldEmail;
    public PasswordField fldConfirmPassword;



    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;


    public void initialize() {
            conn= DB_Connect.connect();
    }

    public void btnRgstrOnMouseClicked(MouseEvent mouseEvent) {
            String username= fldUserName.getText();
            String email= fldEmail.getText();
            String password= fldPassword.getText();
            String confirmPassword= fldConfirmPassword.getText();

        try {
            String sql= "insert into operation (username,email,password,confirmPassword) values('"+username+"','"+email+"','"+password+"','"+confirmPassword+"')";
            pst= conn.prepareStatement(sql);
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
