package com.agency.touristagency.country.town.address.service;

import com.agency.touristagency.country.town.address.Address;
import com.agency.touristagency.service.AbstractServiceClass;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;


    class AddressService extends AbstractServiceClass<Address> implements AddressServiceLocal {
        public AddressService() {
            super(Address.class);
        }

        @Override
        public Address findByName(String name) {
            try {
                Query query =getEntityManager().createNamedQuery("Address.findByName");
                query.setParameter("name", name);
                return (Address) query.getSingleResult();
            }catch (NoResultException exception){
                throw  exception;
            }
        }
    }


