package ru.dlobanov.timereporter;

import ru.dlobanov.timereporter.model.Project;

public class ProjectDeletionEvent {

    private Project project;

    public ProjectDeletionEvent(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
