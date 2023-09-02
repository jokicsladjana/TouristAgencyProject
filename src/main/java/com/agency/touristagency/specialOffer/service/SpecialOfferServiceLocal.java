package com.agency.touristagency.specialOffer.service;

import com.agency.touristagency.specialOffer.SpecialOffer;

import java.util.List;

public interface SpecialOfferServiceLocal {

        SpecialOfferServiceLocal SERVICE=new SpecialOfferService();

        void create(SpecialOffer specialOffer);

        void edit(SpecialOffer specialOffer);

        void remove(SpecialOffer specialOffer);

        void remove(Long id);

        SpecialOffer find(Long id);

        List<SpecialOffer> findAll();
    }


