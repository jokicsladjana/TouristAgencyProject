package com.agency.touristagency.passenger.panel;

import com.agency.touristagency.country.town.address.Address;
import com.agency.touristagency.passenger.Passenger;
import com.agency.touristagency.passenger.service.PassengerServiceLocal;
import com.agency.touristagency.user_interface.Controller;
import com.agency.touristagency.user_interface.paneli.TouristAgencyPanel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.function.Consumer;

public class PassangerPanel extends VBox {
    private Label currentEmployeeLabel = new Label();
    private final Button backButton = new Button("Back");

    private TableView<Passenger> passengerTableView = new TableView<>();

    private ObservableList<Passenger> passengerObservableList;

    private final Button addPassengerButton = new Button("Add Passenger");

    private final Button deletePassengerButton = new Button("Delete Passenger");
    private final CheckBox deleteCheckBox = new CheckBox("Delete");

    //private final Button ordersButton=new Button("Orders");
   // private final Button newOrderButton=new Button("New order");


    public PassangerPanel(){
        setSpacing(10);
        setPadding(new Insets(10));

        BorderPane backAndEmployeePanel = getBackAndEmployeePanel();
        setupTableView();
       // BorderPane searchPanel = getSearchPanel();
        HBox buttonPanel = getButtonPanel();

        getChildren().addAll(backAndEmployeePanel,passengerTableView,buttonPanel);
    }

    private HBox getButtonPanel() {
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(addPassengerButton, deletePassengerButton, deleteCheckBox);
        addPassengerButton.setOnAction(this::onClickAddPassengerButton);
        deletePassengerButton.setOnAction(this::onClickDeletePassengerButton);
        deletePassengerButton.setDisable(true);
        deleteCheckBox.setOnAction(this::onClickDeleteCheckBox);
        return hBox;
    }

    private void onClickAddPassengerButton(ActionEvent actionEvent) {
        Controller.instance().setPassengerPanel(this);
        Scene scene = new Scene(new AddPassengerPanel());
        Stage stage = Controller.instance().getAddPassengerStage();
        stage.setScene(scene);
        stage.setTitle("Add passenger");
        stage.show();
    }

    private void onClickDeletePassengerButton(ActionEvent actionEvent) {
        int numberSelectedPassenger=0;
        for(int i=0;i<passengerObservableList.size();i++){
            if(passengerTableView.getSelectionModel().isSelected(i)){
                numberSelectedPassenger++;
            }
        }
        if (numberSelectedPassenger!=1) {
            Controller.instance().showDialog("Selektujte putnika kojeg želite izbrisati");
        } else {
            Passenger selectedPassenger = passengerTableView.getSelectionModel().getSelectedItem();
            passengerObservableList.remove(selectedPassenger);
            PassengerServiceLocal.SERVICE.remove(selectedPassenger.getId());
        }
    }

    private void onClickDeleteCheckBox(ActionEvent actionEvent) {
        if (deleteCheckBox.isSelected()) {
            deletePassengerButton.setDisable(false);
        } else {
            deletePassengerButton.setDisable(true);
        }
    }

   /* private BorderPane getSearchPanel() {
        HBox searchPanel = new HBox(10);
        searchPanel.getChildren().addAll(searchTextField, searchButton);
        searchTextField.setPromptText("Search name or surname...");
        searchButton.setOnAction(this::onClickSearchButton);
        ordersButton.setOnAction(this::onClickOrdersButton);
        newOrderButton.setOnAction(this::onClickNewOrderButton);

        HBox orderPanel=new HBox(10);
        orderPanel.getChildren().addAll(ordersButton,newOrderButton);
        BorderPane buttonBorderPane = new BorderPane(null, null, orderPanel, null, searchPanel);
        return buttonBorderPane;
    }*/

   /* private void onClickNewOrderButton(ActionEvent actionEvent) {
        int numberSelectedCustomer=0;
        for(int i=0;i<customerObservableList.size();i++){
            if(customerTableView.getSelectionModel().isSelected(i)){
                numberSelectedCustomer++;
            }
        }
        if (numberSelectedCustomer!=1) {
            Controller.instance().showDialog("Selektujte kupca da bi izvršili novu narudžbu");
        }else{
            Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            Scene scene=new Scene(new NewOrderpanel(selectedCustomer));
            Controller.instance().getMainStage().setScene(scene);
            Controller.instance().getMainStage().setTitle("New order");
        }
    }

    private void onClickOrdersButton(ActionEvent actionEvent) {
        int numberSelectedCustomer=0;
        for(int i=0;i<customerObservableList.size();i++){
            if(customerTableView.getSelectionModel().isSelected(i)){
                numberSelectedCustomer++;
            }
        }
        if (numberSelectedCustomer!=1) {
            Controller.instance().showDialog("Selektujte kupca da bi vidili njegove narudžbe");
        } else {
            Controller.instance().setCustomersPanel(this);
            Stage stage=new Stage();
            Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            CustomerOrderPanel customerOrderPanel=new CustomerOrderPanel(selectedCustomer,stage);
            Scene scene=new Scene(customerOrderPanel,500,400);
            stage.setScene(scene);
            stage.setTitle("Narudžbe kupca");
            stage.show();
        }
    }

    private void onClickSearchButton(ActionEvent actionEvent) {
        if (searchTextField.getText().isEmpty()) {
            Controller.instance().showDialog("Unesite ime ili prezime zaposlenika kojeg želite");
        } else {
            for (int i = 0; i < customerObservableList.size(); i++) {
                customerTableView.getSelectionModel().clearSelection();
            }
            for (int i = 0; i < customerObservableList.size(); i++) {
                if (customerObservableList.get(i).getName().equalsIgnoreCase(searchTextField.getText()) || customerObservableList.get(i).getSurname().equalsIgnoreCase(searchTextField.getText())) {
                    customerTableView.getSelectionModel().select(i);
                }
            }
        }
        searchTextField.clear();
    }

   */ private void setupTableView() {
        passengerObservableList = PassengerServiceLocal.SERVICE.loadPassengers();
        passengerTableView.setItems(passengerObservableList);
        passengerTableView.setEditable(true);
        MultipleSelectionModel<Passenger> selectionModel=passengerTableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Passenger, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Passenger, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(100);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setName(event.getNewValue())));

        TableColumn<Passenger, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameColumn.setMinWidth(100);
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setSurname(event.getNewValue())));

       TableColumn<Passenger, String> mobileColumn = new TableColumn<>("Mobitel");
       mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
       mobileColumn.setMinWidth(100);
       mobileColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       mobileColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setMobile(event.getNewValue())));

        TableColumn<Passenger, Address> addressColumn = new TableColumn<>("Adresa");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressColumn.setCellFactory(TextFieldTableCell.<Passenger,Address>forTableColumn(new Converter()));
        addressColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setAddress(event.getNewValue())));


        TableColumn<Passenger, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setMinWidth(150);
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setEmail(event.getNewValue())));


        passengerTableView.getColumns().addAll(idColumn, nameColumn, surnameColumn, mobileColumn, addressColumn, emailColumn);
    }


    private <E> void onFiledChange(TableColumn.CellEditEvent<Passenger, E> event, Consumer<Passenger> passengerConsumer) {
        Passenger editPassenger = event.getRowValue();
        passengerConsumer.accept(editPassenger);
        PassengerServiceLocal.SERVICE.edit(editPassenger);
    }

    private BorderPane getBackAndEmployeePanel() {
        currentEmployeeLabel = Controller.getCurrentEmployeeLabel();
        backButton.setOnAction(this::onClickLogoutButton);
        return new BorderPane(null, null, currentEmployeeLabel, null, backButton);
    }

    private void onClickLogoutButton(ActionEvent actionEvent) {
        Scene scene = new Scene(new TouristAgencyPanel());
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Tourist Agency");
    }

    public ObservableList<Passenger> getPassengerObservableList() {
        return passengerObservableList;
    }

    public void refresh(){
        passengerTableView.refresh();
    }

}
