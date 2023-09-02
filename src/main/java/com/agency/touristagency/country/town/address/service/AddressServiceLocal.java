package com.agency.touristagency.country.town.address.service;

import com.agency.touristagency.country.town.address.Address;

import java.util.List;

    public interface AddressServiceLocal {
        AddressServiceLocal SERVICE = new AddressService();

        void create(Address address);

        void edit(Address address);

        void remove(Address address);

        void remove(Long id);

        Address find(Long id);

        List<Address> findAll();

        Address findByName(String name);
    }


