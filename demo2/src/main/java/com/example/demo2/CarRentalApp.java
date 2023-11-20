package com.example.demo2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    public class CarRentalApp extends Application {

        private List<Car> availableCars;
        private String currentUser;
        private static final String FILE_NAME = "cars.txt";

        public static void main(String[] args) {
            launch(args);
        }
        private void removeRentedCar(Car rentedCar) {
            availableCars.remove(rentedCar);
        }

        private void rentCar() {
            if (availableCars.isEmpty()) {
                showAlert("No cars available for rent.");
                return;
            }

            /*ChoiceDialog<Car> dialog = new ChoiceDialog<>(availableCars.get(0), availableCars);
            dialog.setTitle("Rent a Car");
            dialog.setHeaderText("Select a car to rent:");
            dialog.setContentText("Car:");

            Optional<Car> result = dialog.showAndWait();
            result.ifPresent(car -> {
                car.setRented(true);
                car.setDateRented(java.time.LocalDate.now().toString());
                showAlert("Car rented successfully:\n" + car);

                // Remove the rented car from the availableCars list
                removeRentedCar(car);
            });
        }*/
            List<Car> availableForRent = availableCars.stream()
                    .filter(car -> !car.isRented())
                    .toList();

            if (availableForRent.isEmpty()) {
                showAlert("No cars available for rent.");
                return;
            }

            ChoiceDialog<Car> dialog = new ChoiceDialog<>(availableForRent.get(0), availableForRent);
            dialog.setTitle("Rent a Car");
            dialog.setHeaderText("Select a car to rent:");
            dialog.setContentText("Car:");

            Optional<Car> result = dialog.showAndWait();
            result.ifPresent(car -> {
                car.setRented(true);
                car.setDateRented(java.time.LocalDate.now().toString());
                showAlert("Car rented successfully:\n" + car);
            });
        }


        @Override
        public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
            availableCars = loadCarsFromFile(FILE_NAME);
            currentUser = null;

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
//                    if (authenticateUser(username, password)) {
//                        currentUser = username;
//
//                        primaryStage.setScene(createMainScene());}
                    else {
                        showAlert("Invalid username or password. Please try again.");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    showAlert("Error reading user data.");
                    ex.printStackTrace();
                }
            });

            grid.getChildren().addAll(userLabel,usernameField,usernameLabel,passwordField, loginButton);
            return new Scene(grid, 300, 100);
        }

        private Scene createMainScene() {
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(5);
            grid.setHgap(5);

            Button viewCarsButton = new Button("View Available Cars");
            GridPane.setConstraints(viewCarsButton, 0, 0);
            viewCarsButton.setOnAction(e -> showAvailableCars());

            Button rentCarButton = new Button("Rent a Car");
            GridPane.setConstraints(rentCarButton, 0, 1);
            rentCarButton.setOnAction(e -> rentCar());

            Button addCarButton = new Button("Add a Car");
            GridPane.setConstraints(addCarButton, 0, 2);
            addCarButton.setOnAction(e -> addCar());

            Button viewHistoryButton = new Button("View Rental History");
            GridPane.setConstraints(viewHistoryButton, 0, 3);
            viewHistoryButton.setOnAction(e -> viewRentalHistory());

            /*Button logoutButton = new Button("Logout");
            GridPane.setConstraints(logoutButton, 0, 4);
            logoutButton.setOnAction(e -> {
                try {
                    saveCarsToFile(FILE_NAME, availableCars);
                } catch (IOException ex) {
                    showAlert("Error saving car data.");
                    ex.printStackTrace();
                }
                Stage primaryStage = new Stage();
                primaryStage.setScene(createLoginScene(primaryStage));
            });

            grid.getChildren().addAll(viewCarsButton, rentCarButton, addCarButton, viewHistoryButton, logoutButton);
            return new Scene(grid, 300, 200);
        }*/
            Button logoutButton = new Button("Logout");
            GridPane.setConstraints(logoutButton, 0, 4);
            Stage primaryStage = null;
            logoutButton.setOnAction(e -> logout(primaryStage)); // Call logout method

            grid.getChildren().addAll(viewCarsButton, rentCarButton, addCarButton, viewHistoryButton, logoutButton);
            return new Scene(grid, 300, 200);
        }
        private void logout(Stage primaryStage) {
            try {
                saveCarsToFile(FILE_NAME, availableCars);
            } catch (IOException ex) {
                showAlert("Error saving car data.");
                ex.printStackTrace();
            }

            showAlert("Logout successful.");
            primaryStage.setScene(createLoginScene(primaryStage));
        }


        private void showAvailableCars() {
            if (availableCars.isEmpty()) {
                showAlert("No cars available for rent.");
            } else {
                StringBuilder carsInfo = new StringBuilder("Available Cars:\n");
                for (Car car : availableCars) {
                    carsInfo.append(car).append("\n");
                }
                showAlert(carsInfo.toString());
            }
        }

        /*private void rentCar() {
            if (availableCars.isEmpty()) {
                showAlert("No cars available for rent.");
                return;
            }

            ChoiceDialog<Car> dialog = new ChoiceDialog<>(availableCars.get(0), availableCars);
            dialog.setTitle("Rent a Car");
            dialog.setHeaderText("Select a car to rent:");
            dialog.setContentText("Car:");

            Optional<Car> result = dialog.showAndWait();
            result.ifPresent(car -> {
                car.setRented(true);
                car.setDateRented(java.time.LocalDate.now().toString());
                showAlert("Car rented successfully:\n" + car);
            });
        }*/

        private void addCar() {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add a Car");
            dialog.setHeaderText("Enter car details:");
            dialog.setContentText("Model, Rent Price:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(input -> {
                String[] carDetails = input.split(",");
                if (carDetails.length == 2) {
                    String model = carDetails[0].trim();
                    double rentPrice;
                    try {
                        rentPrice = Double.parseDouble(carDetails[1].trim());
                    } catch (NumberFormatException e) {
                        showAlert("Invalid rent price. Please enter a valid number.");
                        return;
                    }
                    Car newCar = new Car(model, rentPrice, false, null);
                    availableCars.add(newCar);
                    showAlert("Car added successfully:\n" + newCar);
                } else {
                    showAlert("Invalid input format. Please enter model and rent price separated by a comma.");
                }
            });
        }

        private void viewRentalHistory() {
            List<Car> rentedCars = new ArrayList<>();
            for (Car car : availableCars) {
                if (car.isRented()) {
                    rentedCars.add(car);
                }
            }

            if (rentedCars.isEmpty()) {
                showAlert("No rental history available.");
            } else {
                StringBuilder historyInfo = new StringBuilder("Rental History:\n");
                for (Car car : rentedCars) {
                    historyInfo.append(car).append("\n");
                }
                showAlert(historyInfo.toString());
            }
        }


       /* private void logout(Stage primaryStage) {
            try {
                saveCarsToFile(FILE_NAME, availableCars);
            } catch (IOException ex) {
                showAlert("Error saving car data.");
                ex.printStackTrace();
            }

            showAlert("Logout successful.");
            primaryStage.setScene(createLoginScene(primaryStage));
        }*/



        private boolean authenticateUser(String password, String username) throws IOException, ClassNotFoundException {
            return "username".equals(username) && "password".equals(password);
            //return "password".equals(password);
        }



        private void showAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        private List<Car> loadCarsFromFile(String fileName) throws IOException, ClassNotFoundException {
            File file = new File(fileName);
            List<Car> cars;

            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                    cars = (List<Car>) ois.readObject();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Error reading car data from file.", e);
                }
            } else {
                // Create some default cars if the file doesn't exist
                cars = createDefaultCars();
                saveCarsToFile(fileName, cars);
            }

            return cars;
        }
        private List<Car> createDefaultCars() {
            List<Car> defaultCars = new ArrayList<>();
            defaultCars.add(new Car("Toyota Camry", 50.0, false, null));
            defaultCars.add(new Car("Honda Accord", 55.0, false, null));
            defaultCars.add(new Car("Ford Fusion", 48.0, false, null));
            // Add more default cars as needed
            return defaultCars;
        }

        private void saveCarsToFile(String fileName, List<Car> cars) throws IOException {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(cars);
            } catch (FileNotFoundException e) {
                // If the file doesn't exist, create it
                new File(fileName).createNewFile();
                // Save cars again
                saveCarsToFile(fileName, cars);
            }
        }

        private static class Car implements Serializable {
            private String model;
            private double rentPrice;
            private boolean rented;
            private String dateRented;

            public Car(String model, double rentPrice, boolean rented, String dateRented) {
                this.model = model;
                this.rentPrice = rentPrice;
                this.rented = rented;
                this.dateRented = dateRented;
            }

            public String getModel() {
                return model;
            }

            public double getRentPrice() {
                return rentPrice;
            }

            public boolean isRented() {
                return rented;
            }

            public void setRented(boolean rented) {
                this.rented = rented;
            }

            public String getDateRented() {
                return dateRented;
            }

            public void setDateRented(String dateRented) {
                this.dateRented = dateRented;
            }

            @Override
            public String toString() {
                return "Car{" +
                        "model='" + model + '\'' +
                        ", rentPrice=" + rentPrice +
                        ", rented=" + rented +
                        ", dateRented='" + dateRented + '\'' +
                        '}';
            }
        }
    }

