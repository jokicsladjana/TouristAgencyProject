package com.agency.touristagency.user_interface;

import com.agency.touristagency.destination.panel.DestinationPanel;
import com.agency.touristagency.employee.Employee;
import com.agency.touristagency.employee.panel.EmployeePanel;
import com.agency.touristagency.passenger.Passenger;
import com.agency.touristagency.passenger.panel.PassangerPanel;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {
    public static String PU_NAME="agencyPU";

        private static Controller INSTANCE=null;
        private Stage mainStage;
        private Stage addEmployeeStage=new Stage();
        private Stage addPassengerStage=new Stage();
        private Stage addDestinationStage=new Stage();
        private static Employee currentEmployee;

        private EmployeePanel employeePanel;

        private PassangerPanel passengerPanel;

        private DestinationPanel destinationPanel;

        private Controller(){
        }

        public void showDialog(String greška){
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText(greška);
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        }

        public Stage getAddDestinationStage() {
            return addDestinationStage;
        }

        public void setAddDestinationStage (Stage addDestinationStage) {
            this.addDestinationStage = addDestinationStage;
        }

        public DestinationPanel getDestinationPanel() {
            return destinationPanel;
        }

        public void setDestinationPanel(DestinationPanel destinationPanel) {
            this.destinationPanel = destinationPanel;
        }

       public PassangerPanel getPassengerPanel() {
       return passengerPanel;
       }

       public void setPassengerPanel(PassangerPanel passengerPanel) {
          this.passengerPanel = passengerPanel;
        }

        public Stage getAddPassengerStage() {
           return addPassengerStage;
        }

       public void setAddPassengerStage(Stage addPassengerStage) {
            this.addPassengerStage = addPassengerStage;
        }

        public EmployeePanel getEmployeePanel() {
        return employeePanel;
        }

      public void setEmployeePanel(EmployeePanel employeePanel) {
           this.employeePanel = employeePanel;
        }

        public Stage getAddEmployeeStage() {
            return addEmployeeStage;
        }

        public void setAddEmployeeStage (Stage addEmployeeStage) {
            this.addEmployeeStage = addEmployeeStage;
        }

        public static Employee getCurrentEmployee() {
            return currentEmployee;
        }
        public static Label getCurrentEmployeeLabel(){
            Label currentEmployeeLabel=new Label();
            currentEmployeeLabel.setText(Controller.getCurrentEmployee().getName() + ", " + Controller.getCurrentEmployee().getSurname());
            return currentEmployeeLabel;
        }

        public static void setCurrentEmployee(Employee currentEmployee) {
            Controller.currentEmployee = currentEmployee;
        }

        public Stage getMainStage() {
            mainStage.centerOnScreen();
            return mainStage;
        }

        public void setMainStage(Stage mainStage) {
            this.mainStage = mainStage;
        }

        public static Controller instance(){
            if(INSTANCE==null){
                INSTANCE=new Controller();
            }
            return INSTANCE;
        }


    }






