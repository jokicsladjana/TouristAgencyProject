package com.agency.touristagency.passenger.service;

import com.agency.touristagency.passenger.Passenger;
import javafx.collections.ObservableList;

import java.util.List;

public interface PassengerServiceLocal {

        PassengerServiceLocal SERVICE = new PassengerService();

        void create(Passenger passenger);

        void edit(Passenger passenger);

        void remove(Passenger passenger);

        void remove(Long id);

        Passenger find(Long id);

        List<Passenger> findAll();
        ObservableList<Passenger> loadPassengers();
    }


