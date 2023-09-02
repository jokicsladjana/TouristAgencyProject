package com.agency.touristagency.passenger.panel;

import com.agency.touristagency.country.Country;
import com.agency.touristagency.country.service.CountryServiceLocal;
import com.agency.touristagency.country.town.Town;
import com.agency.touristagency.country.town.address.Address;
import com.agency.touristagency.country.town.address.service.AddressServiceLocal;
import com.agency.touristagency.country.town.service.TownServiceLocal;
import com.agency.touristagency.passenger.Passenger;
import com.agency.touristagency.passenger.service.PassengerServiceLocal;
import com.agency.touristagency.user_interface.Controller;
import jakarta.persistence.NoResultException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.List;

public class AddPassengerPanel extends GridPane {
    private final TextField nameTextField = new TextField();
    private final Label nameLabel = new Label("Ime: ");
    private final TextField surnameTextField = new TextField();
    private final Label surnameLabel = new Label("Prezime: ");
    private final Label mobileLabel = new Label("Mobile: ");
    private final TextField mobileTextField = new TextField();
    private final Label emailLabel = new Label("Email: ");
    private final TextField emailTextField = new TextField();
    private final ComboBox<Country> countryComboBox=new ComboBox<>();
    private final Label countryLabel=new Label("Država: ");
    private final ComboBox<Town> townComboBox=new ComboBox<>();
    private final Label townLabel=new Label("Grad: ");
    private final TextField addressTextField = new TextField();
    private final Label addreslabel = new Label("Adresa: ");
    private final Button saveCustomerButton = new Button("SAVE");
    public AddPassengerPanel() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));

        setComponents();
        setUpComboBox();
        addComponents();
    }

    private void setComponents() {
        //forma za dodavanje
        nameTextField.setMaxWidth(200);
        nameTextField.setPromptText("Enter name...");
        surnameTextField.setMaxWidth(200);
        surnameTextField.setPromptText("Enter surname...");
        countryComboBox.setMaxWidth(200);
        townComboBox.setMaxWidth(200);
        addressTextField.setMaxWidth(200);
        addressTextField.setPromptText("Enter address...");
        mobileTextField.setMaxWidth(200);
        mobileTextField.setPromptText("Enter mobile...");
        emailTextField.setMaxWidth(200);
        emailTextField.setPromptText("Enter email...");
        saveCustomerButton.setMaxWidth(50);
        saveCustomerButton.setOnAction(this::onClickSaveCustomerButton);
    }

    private void addComponents() {
        add(nameLabel, 0, 0);
        add(nameTextField, 1, 0);
        add(surnameLabel, 0, 1);
        add(surnameTextField, 1, 1);
        add(countryLabel, 0, 3);
        add(countryComboBox, 1, 3);
        add(townLabel, 0, 4);
        add(townComboBox, 1, 4);
        add(addreslabel, 0, 5);
        add(addressTextField, 1, 5);
        add(mobileLabel, 0, 6);
        add(mobileTextField, 1, 6);
        add(emailLabel, 0, 7);
        add(emailTextField, 1, 7);
        add(saveCustomerButton, 0, 9);
    }

    private void onClickSaveCustomerButton(ActionEvent actionEvent) {
        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() ||
                mobileTextField.getText().isEmpty() || addressTextField.getText().isEmpty() ||
                countryComboBox.getValue()==null || townComboBox.getValue()==null) {
            Controller.instance().showDialog("Niste popunili sva polja!");
        }else{
            Passenger passenger = new Passenger();
            passenger.setName(nameTextField.getText());
            passenger.setSurname(surnameTextField.getText());
            passenger.setMobile(mobileTextField.getText());
            passenger.setEmail(emailTextField.getText());
            try {
                Address addressInBase= AddressServiceLocal.SERVICE.findByName(addressTextField.getText());
                if(addressInBase.getTown()!=townComboBox.getValue()){
                    throw new NoResultException();
                }
                addressInBase.setTown(townComboBox.getValue());
                passenger.setAddress(addressInBase);
            }catch (NoResultException exception){
                Address address=new Address();
                address.setTown(townComboBox.getValue());
                address.setName(addressTextField.getText());
                AddressServiceLocal.SERVICE.create(address);
                passenger.setAddress(address);
            }
            PassengerServiceLocal.SERVICE.create(passenger);
            Controller.instance().getAddPassengerStage().close();
            Controller.instance().getPassengerPanel().getPassengerObservableList().add(passenger);
        }
        clearTextField();
    }

    private void clearTextField() {
        nameTextField.clear();
        surnameTextField.clear();
        emailTextField.clear();
        mobileTextField.clear();
        addressTextField.clear();
    }

    private void setUpComboBox() {
        ObservableList<Country> countryObservableList = countryComboBox.getItems();
        ObservableList<Town> townObservableList=townComboBox.getItems();

        List<Country> countryList = CountryServiceLocal.SERVICE.findAll();
        countryObservableList.addAll(countryList);
        countryComboBox.setValue(countryList.get(0));
        countryComboBox.setOnAction(this::onClickCountryComboBox);

        List<Town> townList= TownServiceLocal.SERVICE.findByCountry(countryComboBox.getValue());
        townObservableList.addAll(townList);


    }

    private void onClickCountryComboBox(ActionEvent actionEvent) {
        townComboBox.getItems().removeAll(townComboBox.getItems());
        ObservableList<Town> townObservableList=townComboBox.getItems();
        List<Town> townList= TownServiceLocal.SERVICE.findByCountry(countryComboBox.getValue());
        townObservableList.addAll(townList);
    }
}

