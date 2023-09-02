package com.agency.touristagency.destination.service;

import com.agency.touristagency.destination.Destination;
import com.agency.touristagency.service.AbstractServiceClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


    class DestinationService extends AbstractServiceClass<Destination> implements DestinationServiceLocal {
        public DestinationService() {
            super(Destination.class);
        }


        @Override
        public ObservableList<Destination> loadDestinations() {
            List<Destination> destination=findAll();
            return FXCollections.observableList(destination);
        }
    }


