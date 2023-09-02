package com.agency.touristagency.destination.destination_detail.service;


import com.agency.touristagency.destination.destination_detail.DestinationDetail;



public interface DestinationDetailServiceLocal {

    DestinationDetailServiceLocal SERVICE=new DestinationDetailService();

    void create(DestinationDetail destinationDetail);

    void edit(DestinationDetail destinationDetail);

    void remove(DestinationDetail destinationDetail);

    void remove(Long id);

    DestinationDetail find(Long id);

    }
