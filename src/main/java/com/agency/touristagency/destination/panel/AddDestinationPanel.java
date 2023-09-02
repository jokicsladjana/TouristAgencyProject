package com.agency.touristagency.destination.panel;

import com.agency.touristagency.destination.Destination;
import com.agency.touristagency.destination.service.DestinationServiceLocal;
import com.agency.touristagency.user_interface.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class AddDestinationPanel extends GridPane {

    private final TextField nameOfDestinationTextField = new TextField();

    private final Label nameOfDestinationLabel = new Label("Ime destinacije: ");

    private final TextField cityTextField = new TextField();

    private final Label cityLabel = new Label("Grad: ");
    private final TextField priceTextField = new TextField();
    private final Label priceLabel = new Label("Cijena: ");

    private final TextField departureDateTextField = new TextField();

    private final Label departureDateLabel = new Label("Datum polaska: ");

    private final TextField returnDateTextField = new TextField();

    private final Label returnDateLabel = new Label("Datum povratka: ");

    private final Button addDestinationButton = new Button("SAVE");

    public AddDestinationPanel() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));

        setComponents();
        addComponents();
    }

    private void addComponents() {
        add(nameOfDestinationLabel, 0, 0);
        add(nameOfDestinationTextField, 1, 0);
        add(cityLabel, 0, 1);
        add(cityTextField, 1, 1);
        add(priceLabel, 0, 2);
        add(priceTextField, 1, 2);
        add(departureDateLabel, 0, 3);
        add(departureDateTextField, 1, 3);
        add(returnDateLabel, 0, 3);
        add(returnDateTextField, 1, 3);
        add(addDestinationButton, 0, 4);
    }

    private void setComponents() {
        //forma za dodavanje
        nameOfDestinationTextField.setMaxWidth(200);
        nameOfDestinationTextField.setPromptText("Enter name of destination...");
        cityTextField.setMaxWidth(200);
        cityTextField.setPromptText("Enter city...");
        priceTextField.setMaxWidth(200);
        priceTextField.setPromptText("Enter price...");
        departureDateTextField.setMaxWidth(200);
        departureDateTextField.setPromptText("Enter departure date...");
        returnDateTextField.setMaxWidth(200);
        returnDateTextField.setPromptText("Enter return date...");

        addDestinationButton.setOnAction(this::onClickAddProductButton);
    }

    private void onClickAddProductButton(ActionEvent actionEvent) {
        if (nameOfDestinationTextField.getText().isEmpty() || cityTextField.getText().isEmpty() ||
                priceTextField.getText().isEmpty() ||departureDateTextField.getText().isEmpty() || returnDateTextField.getText().isEmpty())  {
            Controller.instance().showDialog("Niste unijeli sva polja!");
        } else {
            Destination destination = new Destination();
            destination.setNameOfDestination(nameOfDestinationTextField.getText());
            destination.setCity(cityTextField.getText());
            destination.setPrice(Double.parseDouble(priceTextField.getText()));
            destination.setDepartureDate(LocalDate.parse(departureDateTextField.getText()));
            destination.setReturnDate(LocalDate.parse(returnDateTextField.getText()));
            DestinationServiceLocal.SERVICE.create(destination);
            Controller.instance().getAddDestinationStage().close();
            Controller.instance().getDestinationPanel().getDestinationObservableList().add(destination);
        }
        clearTextField();
    }

    private void clearTextField() {
        nameOfDestinationTextField.clear();
        cityTextField.clear();
        priceTextField.clear();
        departureDateTextField.clear();
        returnDateTextField.clear();
    }
}


