package com.agency.touristagency.user_interface.paneli;

import com.agency.touristagency.destination.panel.DestinationPanel;
import com.agency.touristagency.employee.panel.EmployeePanel;
import com.agency.touristagency.passenger.panel.PassangerPanel;
import com.agency.touristagency.user_interface.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class TouristAgencyPanel extends VBox {

    private Hyperlink currentEmployeeLink = new Hyperlink();

    private final Label touristAgencyLabel = new Label("Tourist Agency");

        private final Button passengerButton = new Button("Passengers");
        private final Button employeesButton = new Button("Employees");

        private final Button destinationButton = new Button("Destinations");

        private final Button specialOfferButton = new Button("Special Offers");
        private final Button logoutButton = new Button("Odjava");
       // private final Label dateLabel = new Label();


    public TouristAgencyPanel() {
        setSpacing(10);
        setPadding(new Insets(10));

        BorderPane logoutAndEmployeePanel = getLogoutAndEmployeePanel();
        BorderPane touristAgencyPanel = getTouristAgencyPanel();

        buttonSetOnAction();

        getChildren().addAll(logoutAndEmployeePanel, touristAgencyPanel );
    }

    private void buttonSetOnAction() {
        destinationButton.setOnAction(this::onClickDestinationButton);
        employeesButton.setOnAction(this::onClickEmployeesButton);
        passengerButton.setOnAction(this::onClickPassengerButton);
        specialOfferButton.setOnAction(this::onClickSpecialOfferButton);
    }

    private void onClickPassengerButton(ActionEvent actionEvent) {
        Scene scene = new Scene(new PassangerPanel());
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Customers");
    }

    private void onClickEmployeesButton(ActionEvent actionEvent) {
        Scene scene = new Scene(new EmployeePanel());
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Employees");
    }

    private void onClickDestinationButton(ActionEvent actionEvent) {
        Scene scene = new Scene(new DestinationPanel());
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Destination");
    }
    private void onClickSpecialOfferButton(ActionEvent actionEvent) {
        Scene scene = new Scene(new DestinationPanel());
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Special Offers");
    }

    private BorderPane getLogoutAndEmployeePanel() {
        currentEmployeeLink.setText(Controller.getCurrentEmployeeLabel().getText());

        logoutButton.setOnAction(this::onClickLogoutButton);
        return new BorderPane(null, null, currentEmployeeLink, null, logoutButton);
    }





    private VBox getButtonPanel() {
        if (Controller.getCurrentEmployee().getPrivilege().getName().equals("user")) {
            employeesButton.setDisable(true);
        }
        destinationButton.setMinWidth(120);
        passengerButton.setMinWidth(120);
        employeesButton.setMinWidth(120);
        specialOfferButton.setMinWidth(120);

        VBox buttonPanel = new VBox(destinationButton, passengerButton,specialOfferButton,employeesButton);
        buttonPanel.setSpacing(10);
        return buttonPanel;
    }

    private BorderPane getTouristAgencyPanel() {
        BorderPane touristAgencyPanel = new BorderPane(touristAgencyLabel);
        touristAgencyPanel.setPadding(new Insets(20));
        touristAgencyLabel.setFont(new Font("Arial", 35));
        return touristAgencyPanel;
    }


    private void onClickLogoutButton(ActionEvent actionEvent) {
        LoginPanel loginPanel = new LoginPanel();
        Scene scene = new Scene(loginPanel);
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Login");
        Controller.setCurrentEmployee(null);
    }

}
