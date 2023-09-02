module com.agency.touristagency {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires org.controlsfx.controls;
    requires java.sql;
    requires java.sql.rowset;

    opens com.agency.touristagency to javafx.base,org.hibernate.orm.core,javafx.fxml;
    opens com.agency.touristagency.employee to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.employee.privilege to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.employee.privilege.service to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.service to javafx.base,org.hibernate.orm.core,jakarta.persistence;
    opens com.agency.touristagency.destination.service to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.destination.panel to javafx.base, org.hibernate.orm.core;
    opens com.agency.touristagency.destination.destination_detail to javafx.base, org.hibernate.orm.core;
    opens com.agency.touristagency.destination.destination_detail.service to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.passenger to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.specialOffer to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.country to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.country.town to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.country.town.address to javafx.base,org.hibernate.orm.core;
    opens com.agency.touristagency.country.town.address.service  to javafx.base,org.hibernate.orm.core;




    exports com.agency.touristagency;
    exports com.agency.touristagency.user_interface;
    exports com.agency.touristagency.user_interface.paneli;
    exports com.agency.touristagency.employee;





}