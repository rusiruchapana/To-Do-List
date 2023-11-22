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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupFormController {
    public Button btnRgstr;
    public TextField fldUserName;
    public PasswordField fldPassword;
    public TextField fldEmail;
    public PasswordField fldConfirmPassword;
    public Label lblUsernameError;
    public Label lblEmailError;
    public Label lblPasswordError;
    public Label lblConfirmPasswordError;
    public Label lblClickLogin;
    public AnchorPane root;


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
            int errors =0;

            if(username.isEmpty()){
                lblUsernameError.setText("Username is empty.");
                errors++;
            }

            if(email.isEmpty()){
                lblEmailError.setText("Email is empty.");
                errors++;
            }else{
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    Pattern pattern = Pattern.compile(EMAIL_PATTERN);

                    Matcher matcher = pattern.matcher(email);
                    boolean isvalid = matcher.matches();

                    if(!isvalid){
                        lblEmailError.setText("Email is not a valid Email.");
                        errors++;
                    }

            }



            if(password.isEmpty()){
                    lblPasswordError.setText("Password is Empty.");
                    errors++;
            }




            if(confirmPassword.isEmpty()){
                    lblConfirmPasswordError.setText("Confirm password is Empty.");
                    errors++;
            }else{
                    if(!confirmPassword.equals(password)){
                          lblConfirmPasswordError.setText("Given 2 passwords are not same.");
                          errors++;
                    }
            }


            if(errors==0){

                    String query ="select * from operation where email=?";
                    pst = conn.prepareStatement(query);
                    pst.setString(1, fldEmail.getText());
                    rs= pst.executeQuery();

                    if(rs.next()){
                            JOptionPane.showMessageDialog(null, "Already exist this user");
                    }else{
                            String sql= "insert into operation (username,email,password,confirmPassword) values('"+username+"','"+email+"','"+password+"','"+confirmPassword+"')";
                            pst= conn.prepareStatement(sql);
                            pst.execute();

                            lblUsernameError.setText("");
                            lblEmailError.setText("");
                            lblPasswordError.setText("");
                            lblConfirmPasswordError.setText("");

                            JOptionPane.showMessageDialog(null, "Log in Successfully.");
                    }

            }else {
                JOptionPane.showMessageDialog(null, "Fields data's are not filled correctly.");
            }



        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }


    }

    public void lblClickLoginOnMouseClicked(MouseEvent mouseEvent) throws IOException {
                Parent parent = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
                Scene scene = new Scene(parent);

                Stage stage= (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login Form");
                stage.centerOnScreen();
                stage.show();



    }
}
