package com.example.vehiclerentalsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {


        primaryStage.setTitle("Car Rental System - Login");
        primaryStage.setScene(createLoginScene(primaryStage));
        primaryStage.show();
    }

    private Scene createLoginScene(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        GridPane.setConstraints(usernameField, 1, 0);

        Label userLabel = new Label("Password:");
        GridPane.setConstraints(userLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            try {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (authenticateUser(passwordField.getText(),usernameField.getText()) )
                {
                    primaryStage.setScene(createMainScene());
                }

                else {
                    System.out.println("Invalid username or password. Please try again.");
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error reading user data.");

                ex.printStackTrace();
            }
        });

        grid.getChildren().addAll(userLabel,usernameField,usernameLabel,passwordField, loginButton);
        return new Scene(grid, 300, 100);
    }
    private boolean authenticateUser(String password, String username) throws IOException, ClassNotFoundException {
        return "username".equals(username) && "password".equals(password);
        //return "password".equals(password);
    }





















//    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
//
//        primaryStage.setTitle("Car Rental System - Login");
//        primaryStage.setScene(createLoginScene(primaryStage));
//        primaryStage.show();
//    }
//
//    private Scene createLoginScene(Stage primaryStage) {
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10, 10, 10, 10));
//        grid.setVgap(5);
//        grid.setHgap(5);
//
//
//        Label userLabel = new Label();
//        GridPane.setConstraints(userLabel, 0, 0);
//        PasswordField passwordField = new PasswordField();
//        GridPane.setConstraints(passwordField, 1, 0);
//
//        Button loginButton = new Button("Login");
//        GridPane.setConstraints(loginButton, 1, 1);
//        loginButton.setOnAction(e -> {
//            try {
//                if (authenticateUser(passwordField.getText())) {
//                    primaryStage.setScene(createMainScene());
//                } else {
//                    System.out.println("Invalid password. Please try again.");
//                }
//            } catch (IOException | ClassNotFoundException ex) {
//                System.out.println("Error reading user data.");
//                ex.printStackTrace();
//            }
//        });
//
//        grid.getChildren().addAll(userLabel, passwordField, loginButton);
//        return new Scene(grid, 300, 100);
//    }
    private Scene createMainScene() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        return new Scene(grid, 300, 200);
    }
//    private boolean authenticateUser(String password) throws IOException, ClassNotFoundException {
//        return "password".equals(password);
//    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch();
    }
}