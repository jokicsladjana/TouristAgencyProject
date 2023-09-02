package com.agency.touristagency.destination.service;

import com.agency.touristagency.destination.Destination;
import javafx.collections.ObservableList;

import java.util.List;

public interface DestinationServiceLocal {
    DestinationServiceLocal SERVICE=new DestinationService();

    void create(Destination destination);

    void edit(Destination destination);

    void remove(Destination destination);
    void remove(Long id);

    Destination find(Long id);

    List<Destination> findAll();
    ObservableList<Destination> loadDestinations();

}

