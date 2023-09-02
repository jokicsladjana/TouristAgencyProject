package com.agency.touristagency.passenger.service;

import com.agency.touristagency.passenger.Passenger;
import com.agency.touristagency.service.AbstractServiceClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

class PassengerService extends AbstractServiceClass<Passenger> implements PassengerServiceLocal{
    public PassengerService() {
        super(Passenger.class);
    }

    @Override
    public ObservableList<Passenger> loadPassengers() {
        List<Passenger> passengers=findAll();
        return FXCollections.observableList(passengers);

    }
}


