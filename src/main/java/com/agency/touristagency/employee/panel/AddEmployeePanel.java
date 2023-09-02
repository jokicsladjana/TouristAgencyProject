package com.agency.touristagency.employee.panel;

import com.agency.touristagency.employee.Employee;
import com.agency.touristagency.employee.privilege.Privilege;
import com.agency.touristagency.employee.privilege.service.PrivilegeServiceLocal;
import com.agency.touristagency.employee.service.EmployeeServiceLocal;
import com.agency.touristagency.user_interface.Controller;
import com.agency.touristagency.user_interface.paneli.PlainPassHashGenerator;
import jakarta.persistence.NoResultException;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddEmployeePanel extends GridPane {
    private final TextField nameTextField = new TextField();
    private final Label nameLabel = new Label("Ime: ");
    private final TextField surnameTextField = new TextField();
    private final Label surnameLabel = new Label("Prezime: ");
    private final TextField usernameTextField = new TextField();
    private final Label usernameLabel = new Label("Korisničko ime: ");

    private final PasswordField passwordTextField = new PasswordField();
    private final Label passwordLabel = new Label("Lozinka: ");

    private final TextField emailTextField = new TextField();

    private final Label emailLabel = new Label("Email: ");
    private final RadioButton adminRadioButton = new RadioButton("Admin");
    private final RadioButton userRadioButton = new RadioButton("User");
    private final Button addEmployeeButton = new Button("SAVE");

    public AddEmployeePanel(){
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));

        setComponents();
        addComponents();
    }

    private void addComponents() {
        add(nameLabel, 0, 0);
        add(nameTextField, 1, 0);
        add(surnameLabel, 0, 1);
        add(surnameTextField, 1, 1);
        add(usernameLabel, 0, 2);
        add(usernameTextField, 1, 2);
        add(passwordLabel, 0, 3);
        add(passwordTextField, 1, 3);
        add(emailLabel, 0,4);
        add(emailTextField,1,4);
    }

    private void setComponents() {
        //forma za dodavanje
        nameTextField.setMaxWidth(200);
        nameTextField.setPromptText("Enter name...");
        surnameTextField.setMaxWidth(200);
        surnameTextField.setPromptText("Enter surname...");
        usernameTextField.setMaxWidth(200);
        usernameTextField.setPromptText("Enter username...");
        passwordTextField.setMaxWidth(200);
        passwordTextField.setPromptText("Enter password...");
        emailTextField.setMaxWidth(200);
        emailTextField.setPromptText("Enter mail...");

        ToggleGroup toggleGroup = new ToggleGroup();
        adminRadioButton.setToggleGroup(toggleGroup);
        userRadioButton.setToggleGroup(toggleGroup);
        userRadioButton.setSelected(true);
        HBox radioButtonPanel = new HBox(10);
        radioButtonPanel.getChildren().addAll(adminRadioButton, userRadioButton);
        add(radioButtonPanel, 0, 5);

        add(addEmployeeButton, 0, 6);
        addEmployeeButton.setOnAction(this::onClickAddEmployeeButton);
    }

    private void onClickAddEmployeeButton(ActionEvent actionEvent) {
        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || emailTextField.getText().isEmpty()) {
            Controller.instance().showDialog("Niste popunili sva polja!");
        } else {
            try {
                Employee employee= EmployeeServiceLocal.SERVICE.findbyUsername(usernameTextField.getText());
                if (employee != null) {
                    Controller.instance().showDialog("Korisničko ime je zauzeto!");
                }
            } catch (NoResultException e) {
                if (passwordTextField.getText().length() < 6) {
                    Controller.instance().showDialog("Lozinka je prekratka (minimalno 6 karaktera)!");
                }else{
                    Employee employee = new Employee();
                    employee.setName(nameTextField.getText());
                    employee.setSurname(surnameTextField.getText());
                    employee.setUsername(usernameTextField.getText());
                    String hashedPassword = new PlainPassHashGenerator().generateHashedPassword(passwordTextField.getText());
                    employee.setPassword(hashedPassword);
                    employee.setEmail(emailTextField.getText());
                    if (adminRadioButton.isSelected()) {
                        Privilege privilege = PrivilegeServiceLocal.SERVICE.find(1l);
                        employee.setPrivilege(privilege);
                    } else {
                        Privilege privilege = PrivilegeServiceLocal.SERVICE.find(2l);
                        employee.setPrivilege(privilege);
                    }
                    EmployeeServiceLocal.SERVICE.create(employee);
                    Controller.instance().getAddEmployeeStage().close();
                    Controller.instance().getEmployeePanel().getEmployeeObservableList().add(employee);
                }
            }
        }
        clearTextField();
    }

    private void clearTextField() {
        nameTextField.clear();
        surnameTextField.clear();
        usernameTextField.clear();
        passwordTextField.clear();
        emailTextField.clear();
    }
}



