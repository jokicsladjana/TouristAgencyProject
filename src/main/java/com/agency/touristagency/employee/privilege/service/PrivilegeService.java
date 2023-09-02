package com.agency.touristagency.employee.privilege.service;

import com.agency.touristagency.employee.privilege.Privilege;
import com.agency.touristagency.service.AbstractServiceClass;

class PrivilegeService extends AbstractServiceClass<Privilege> implements PrivilegeServiceLocal {
    public PrivilegeService() {
        super(Privilege.class);
    }
}
