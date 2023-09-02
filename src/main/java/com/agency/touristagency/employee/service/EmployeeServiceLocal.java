package com.agency.touristagency.employee.service;

import com.agency.touristagency.employee.Employee;
import javafx.collections.ObservableList;

import java.util.List;

public interface EmployeeServiceLocal {
    EmployeeServiceLocal SERVICE = new EmployeeService();

    void create(Employee employee);

    void edit(Employee employee);

    void remove(Employee employee);

    void remove(Long id);

    Employee find(Long id);

    List<Employee> findAll();

    Employee findbyUsername(String username);

    ObservableList<Employee> loadEmployee();

}


