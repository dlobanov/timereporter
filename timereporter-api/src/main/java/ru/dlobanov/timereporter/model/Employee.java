package ru.dlobanov.timereporter.model;

import java.util.List;

public interface Employee {
    String getName();

    OrgUnit getUnit();

    List<EmployeeRole> getRoles();

    String getLogin();
}
