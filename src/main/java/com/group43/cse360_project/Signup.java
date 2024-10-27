package com.group43.cse360_project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static com.group43.cse360_project.UserDB.*;

public class Signup {
    private Stage stage;

    public Signup(Stage stage){
        this.stage = stage;
    }
    public Scene signupScene(){
        double sceneWidth = 1200;
        double sceneHeight = 675;

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F4F4D8;");


        /*
         ************************************************
         * Title
         ************************************************
         */
        Label text = new Label("Signup");
        text.setStyle("-fx-font-size: 24px;");

        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(200);

        VBox titleBox = new VBox(logoImageView, text);
        titleBox.setAlignment(Pos.CENTER);
        root.setTop(titleBox);

        /*
         ************************************************
         * Signup field
         ************************************************
         */
        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-size: 16px;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter name");

        Label idLabel = new Label("ASU ID:");
        idLabel.setStyle("-fx-font-size: 16px;");
        TextField idField = new TextField();
        idField.setPromptText("Enter ID");

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-font-size: 16px;");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");


        // Password Field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 16px;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setStyle("-fx-font-size: 16px;");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Retype password");

        // Grid of signup fields
        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(nameLabel, 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(idLabel, 0, 1);
        inputGrid.add(idField, 1, 1);
        inputGrid.add(emailLabel, 0, 2);
        inputGrid.add(emailField, 1, 2);
        inputGrid.add(passwordLabel, 0, 3);
        inputGrid.add(passwordField, 1, 3);
        inputGrid.add(confirmPasswordLabel, 0, 4);
        inputGrid.add(confirmPasswordField, 1, 4);

        root.setCenter(inputGrid);
        
        //confirm button
        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-font-size: 16px;");
        confirmButton.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            UserType userType = UserType.BUYER;
            //TODO: turn sout to label
            if (name.isEmpty() || id.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                System.out.println("All fields must be filled");
            } else if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match");
            } else {
                //TODO:This doesnt work
                try {
                    UserDB.addNewUser(name, password, userType);
                    switchToLogin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        VBox buttonBox = new VBox(confirmButton);
        buttonBox.setAlignment(Pos.CENTER);

        root.setBottom(buttonBox);

        return new Scene(root, sceneWidth, sceneHeight);
    }
    private void switchToLogin(){
        Login login = new Login(stage);
        Scene scene = login.loginScene();
        stage.setScene(scene);
    }
}