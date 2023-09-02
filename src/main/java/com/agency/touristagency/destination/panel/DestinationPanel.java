package com.agency.touristagency.destination.panel;

import com.agency.touristagency.destination.Destination;
import com.agency.touristagency.destination.service.DestinationServiceLocal;
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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.function.Consumer;

public class DestinationPanel extends VBox {

        private Label currentEmployeeLabel = new Label();
        private final Button backButton = new Button("Back");
        private TableView<Destination> destinationTableView = new TableView<>();
        private ObservableList<Destination> destinationObservableList;
        private TextField searchTextField = new TextField();
        private Button searchButton = new Button("Search");
        private final Button addDestinationButton = new Button("Add Destination");
        private final Button deleteDestinationButton = new Button("Delete Destination");
        private final CheckBox deleteCheckBox = new CheckBox("Delete");
        private final RadioButton nameOfDestinationRadioButton=new RadioButton("Name of destination search");
        private final TextField nameOfDestinationTextField=new TextField();
        private final RadioButton cityRadioButton=new RadioButton("City search");
        private final RadioButton priceRadioButton=new RadioButton("Price search");
        private final RadioButton departureDate=new RadioButton("Departure date search");
        private final RadioButton returnDate=new RadioButton("Return date search");
        private final Button getDestinationButton=new Button("Get Destination");

        public  DestinationPanel(){
            setSpacing(10);
            setPadding(new Insets(10));

            BorderPane backAndEmployeePanel = getBackAndEmployeePanel();
            setupTableView();
            HBox radioHBox=getRadioPanel();
            HBox searchHBox=getSearchPanel();
            HBox getDestinationHBox=getDestinationPanel();
            HBox buttonPanel=getButtonPanel();

            getChildren().addAll(backAndEmployeePanel,destinationTableView,radioHBox,searchHBox,getDestinationHBox,buttonPanel);
        }

        private HBox getButtonPanel() {
            HBox buttonHBox = new HBox(10);
            buttonHBox.getChildren().addAll(addDestinationButton, deleteDestinationButton, deleteCheckBox);
            addDestinationButton.setOnAction(this::onClickAddCustomerButton);
            deleteDestinationButton.setOnAction(this::onClickDeleteCustomerButton);
            deleteDestinationButton.setDisable(true);
            deleteCheckBox.setOnAction(this::onClickDeleteCheckBox);
            return buttonHBox;
        }

        private void onClickAddCustomerButton(ActionEvent actionEvent) {
            Controller.instance().setDestinationPanel(this);
            Scene scene = new Scene(new AddDestinationPanel());
            Stage stage = Controller.instance().getAddDestinationStage();
            stage.setScene(scene);
            stage.setTitle("Add destination");
            stage.show();
        }

        private void onClickDeleteCustomerButton(ActionEvent actionEvent) {
            int numberSelectedDestinations=0;
            for(int i=0;i<destinationObservableList.size();i++){
                if(destinationTableView.getSelectionModel().isSelected(i)){
                    numberSelectedDestinations++;
                }
            }
            if (numberSelectedDestinations!=1) {
                Controller.instance().showDialog("Selektujte destinaciju koju želite izbrisati");
            } else {
                Destination selectedDestination = destinationTableView.getSelectionModel().getSelectedItem();
                destinationObservableList.remove(selectedDestination);
                DestinationServiceLocal.SERVICE.remove(selectedDestination.getId());
            }
        }

        private void onClickDeleteCheckBox(ActionEvent actionEvent) {
            if (deleteCheckBox.isSelected()) {
                deleteDestinationButton.setDisable(false);
            } else {
                deleteDestinationButton.setDisable(true);
            }
        }

        private HBox getDestinationPanel() {
            nameOfDestinationTextField.setPromptText("Name of destination..");
            getDestinationButton.setOnAction(this::onClickGetButton);
            getDestinationButton.setMinWidth(70);
            HBox getItemHBox=new HBox(20);
            getItemHBox.getChildren().addAll(nameOfDestinationTextField,getDestinationButton);
            return getItemHBox;
        }

        private void onClickGetButton(ActionEvent actionEvent) {
            ObservableList<Destination> selectedDestinations = destinationTableView.getSelectionModel().getSelectedItems();
            int numberDestination=0;
            for (int i=0;i<selectedDestinations.size();i++){
                numberDestination+=selectedDestinations.get(i).getPrice();
            }
            nameOfDestinationTextField.setText(String.valueOf(numberDestination));
        }

        private HBox getSearchPanel() {
            searchTextField.setPromptText("Search name of destination..");
            searchButton.setOnAction(this::onClickSearchButton);
            searchButton.setMinWidth(70);
            HBox searchHBox = new HBox(20);
            searchHBox.getChildren().addAll(searchTextField, searchButton);
            return searchHBox;
        }

        private void onClickSearchButton(ActionEvent actionEvent) {
            ObservableList<Destination> list = destinationTableView.getItems();
            for (int i = 0; i < list.size(); i++) {
                destinationTableView.getSelectionModel().clearSelection();
            }
            for (int i = 0; i < list.size(); i++) {
                if (nameOfDestinationRadioButton.isSelected()) {
                    if (list.get(i).getNameOfDestination().equalsIgnoreCase(searchTextField.getText())) {
                        destinationTableView.getSelectionModel().select(i);
                    }
                }else if(cityRadioButton.isSelected()){
                    if (list.get(i).getCity().equalsIgnoreCase(searchTextField.getText())) {
                        destinationTableView.getSelectionModel().select(i);
                    }
                }
            }
            searchTextField.clear();
        }

        private HBox getRadioPanel() {
            nameOfDestinationRadioButton.setSelected(true);
            nameOfDestinationRadioButton.setOnAction(this::onClickSearchRadioButton);
            cityRadioButton.setOnAction(this::onClickSearchRadioButton);
            ToggleGroup searchGroup = new ToggleGroup();
            nameOfDestinationRadioButton.setToggleGroup(searchGroup);
            cityRadioButton.setToggleGroup(searchGroup);
            HBox searchRadioBox = new HBox(10);
            searchRadioBox.getChildren().addAll(nameOfDestinationRadioButton, cityRadioButton);
            return searchRadioBox;
        }

        private void onClickSearchRadioButton(ActionEvent actionEvent) {
            if (actionEvent.getSource() == nameOfDestinationRadioButton) {
                searchTextField.setPromptText("Search name of destination..");
            } else if (actionEvent.getSource() == cityRadioButton) {
                searchTextField.setPromptText("Search city..");
            }
        }

        private void setupTableView() {
            destinationObservableList = DestinationServiceLocal.SERVICE.loadDestinations();
            destinationTableView.setItems(destinationObservableList);
            destinationTableView.setEditable(true);
            MultipleSelectionModel<Destination> selectionModel=destinationTableView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

            TableColumn<Destination, String> nameOfDestinationColumn = new TableColumn<>("Ime destinacije");
            nameOfDestinationColumn.setMinWidth(200);
            nameOfDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("nameOfDestination"));
            nameOfDestinationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            nameOfDestinationColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setNameOfDestination(event.getNewValue())));

            TableColumn<Destination, String> cityColumn = new TableColumn<>("Grad");
            cityColumn.setMinWidth(200);
            cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
            cityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            cityColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setCity(event.getNewValue())));

            TableColumn<Destination, Double> priceColumn = new TableColumn<>("Cijena");
            priceColumn.setMinWidth(200);
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceColumn.setCellFactory(TextFieldTableCell.<Destination, Double>forTableColumn(new DoubleStringConverter()));
            priceColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setPrice(event.getNewValue())));

            TableColumn<Destination, LocalDate> departureDateColumn = new TableColumn<>("Datum polaska");
            departureDateColumn.setMinWidth(200);
            departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
            departureDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            departureDateColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setDepartureDate(event.getNewValue())));

            TableColumn<Destination, LocalDate> returnDateColumn = new TableColumn<>("Datum povratka");
            returnDateColumn.setMinWidth(200);
            returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
            returnDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            returnDateColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setReturnDate(event.getNewValue())));



            destinationTableView.getColumns().addAll(nameOfDestinationColumn, cityColumn, priceColumn, departureDateColumn,returnDateColumn);
        }

        private <E> void onFiledChange(TableColumn.CellEditEvent<Destination, E> event, Consumer<Destination> destinationConsumer) {
            Destination editDestination = event.getRowValue();
            destinationConsumer.accept(editDestination);
            DestinationServiceLocal.SERVICE.edit(editDestination);
        }

        private BorderPane getBackAndEmployeePanel() {
            currentEmployeeLabel = Controller.getCurrentEmployeeLabel();
            backButton.setOnAction(this::onClickLogoutButton);
            return new BorderPane(null, null, currentEmployeeLabel, null, backButton);
        }

        private void onClickLogoutButton(ActionEvent actionEvent) {
            Scene scene = new Scene(new TouristAgencyPanel());
            Controller.instance().getMainStage().setScene(scene);
            Controller.instance().getMainStage().setTitle("TouristAgency");
        }

        public ObservableList<Destination> getDestinationObservableList() {
            return destinationObservableList;
        }
    }


