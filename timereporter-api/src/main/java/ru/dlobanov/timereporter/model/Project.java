package ru.dlobanov.timereporter.model;

import java.util.Optional;

public interface Project {

    String getName();

    String getAlias();

    String getDescription();

    Optional<Employee> getManager();
}
