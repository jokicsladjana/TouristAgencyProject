package com.agency.touristagency.employee.service;

import com.agency.touristagency.employee.Employee;
import com.agency.touristagency.service.AbstractServiceClass;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


    class EmployeeService extends AbstractServiceClass<Employee> implements EmployeeServiceLocal{
        public EmployeeService() {
            super(Employee.class);
        }

        @Override
        public Employee findbyUsername(String username) {
            try {
                Query query =getEntityManager().createNamedQuery("Employee.findByUsername");
                query.setParameter("username", username);
                return (Employee) query.getSingleResult();
            }catch (NoResultException exception){
                throw  exception;
            }
        }

        @Override
        public ObservableList<Employee> loadEmployee() {
            List<Employee> employees=findAll();
            return FXCollections.observableList(employees);
        }
    }


