package com.agency.touristagency.country.town.service;

import com.agency.touristagency.country.Country;
import com.agency.touristagency.country.town.Town;

import java.util.List;

public interface TownServiceLocal {
    TownServiceLocal SERVICE = new TownService();

    void create(Town town);

    void edit(Town town);

    void remove(Town town);

    void remove(Long id);

    Town find(Long id);

    List<Town> findAll();

    List<Town> findByCountry(Country country);
    Town findByName(String name);
}


