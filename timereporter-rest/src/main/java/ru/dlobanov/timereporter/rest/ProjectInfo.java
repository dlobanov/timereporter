package ru.dlobanov.timereporter.rest;

import ru.dlobanov.timereporter.model.Project;

public class ProjectInfo {

    public String name;
    public String alias;
    public String description;
    public String manager;

    public ProjectInfo() {
    }

    public ProjectInfo(Project project) {
        this.name = project.getName();
        this.alias = project.getAlias();
        this.description = project.getDescription();
        this.manager = project.getManager() != null ? project.getManager().getName() : "None";
    }
}
