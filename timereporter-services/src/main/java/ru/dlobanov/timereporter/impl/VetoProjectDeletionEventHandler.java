package ru.dlobanov.timereporter.impl;

import ru.dlobanov.timereporter.ProjectDeletionEvent;
import ru.dlobanov.timereporter.VetoProjectDeletionException;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.transaction.SystemException;

public class VetoProjectDeletionEventHandler {

    public void deleteProject(@Observes(during = TransactionPhase.IN_PROGRESS) ProjectDeletionEvent projectDeletionEvent) throws SystemException {
        System.out.println("Project " + projectDeletionEvent.getProject().getName() + " is going to be deleted?");
        if (projectDeletionEvent.getProject().getAlias().equals("ALU")) {
            throw new VetoProjectDeletionException(projectDeletionEvent.getProject());
        }
    }
}
