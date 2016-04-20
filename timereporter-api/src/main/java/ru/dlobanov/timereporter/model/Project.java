package ru.dlobanov.timereporter.model;

public interface Project {
    String getName();

    String getAlias();

    String getDescription();

    Employee getManager();
}
