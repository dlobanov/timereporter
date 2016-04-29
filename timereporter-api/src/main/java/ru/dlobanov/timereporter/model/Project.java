package ru.dlobanov.timereporter.model;

import java.util.List;
import java.util.Optional;

public interface Project {

    String getName();

    String getAlias();

    String getDescription();

    Optional<Employee> getManager();

    List<? extends OrgUnit> getUnits();
}
