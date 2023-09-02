package com.agency.touristagency.country.service;

import com.agency.touristagency.country.Country;
import com.agency.touristagency.service.AbstractServiceClass;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;


    class CountryService extends AbstractServiceClass<Country> implements CountryServiceLocal {
        public CountryService() {
            super(Country.class);
        }

        @Override
        public Country findByName(String name) {
            try {
                Query query =getEntityManager().createNamedQuery("Country.findByName");
                query.setParameter("name", name);
                return (Country) query.getSingleResult();
            }catch (NoResultException exception){
                throw  exception;
            }
        }
    }


