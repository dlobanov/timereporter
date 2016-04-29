package ru.dlobanov.timereporter;

import ru.dlobanov.timereporter.model.Project;

import java.util.List;

public interface ProjectService {

    Project createOrUpdateProject(String alias, String name, String description, String manager);

    void deleteProject(Project project);

    List<Project> getProjects();

    Project getProject(String alias);

}
