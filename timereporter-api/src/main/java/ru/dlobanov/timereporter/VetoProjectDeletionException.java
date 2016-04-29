package ru.dlobanov.timereporter;

import ru.dlobanov.timereporter.model.Project;

import javax.ejb.ApplicationException;

@ApplicationException
public class VetoProjectDeletionException extends RuntimeException {

    public VetoProjectDeletionException(Project project) {
        super("Project " + project.getName() + " is in use and can't be deleted!");
    }
}
