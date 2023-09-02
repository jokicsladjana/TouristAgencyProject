package com.agency.touristagency.employee.privilege.service;

import com.agency.touristagency.employee.privilege.Privilege;

import java.util.List;

public interface PrivilegeServiceLocal {
    PrivilegeServiceLocal SERVICE=new PrivilegeService();

    void create(Privilege privilege);

    void edit(Privilege privilege);

    void remove(Privilege privilege);
    void remove(Long id);

    Privilege find(Long id);

    List<Privilege> findAll();
}


