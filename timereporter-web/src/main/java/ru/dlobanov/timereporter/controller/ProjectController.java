package ru.dlobanov.timereporter.controller;

import ru.dlobanov.timereporter.OrgStructureService;
import ru.dlobanov.timereporter.ProjectService;
import ru.dlobanov.timereporter.VetoProjectDeletionException;
import ru.dlobanov.timereporter.controller.beans.ProjectBean;
import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.Project;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ProjectController implements Serializable {

    @Inject
    private ProjectService projectService;

    @Inject
    private OrgStructureService orgStructureService;

    public void createOrUpdateProject(ActionEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ProjectBean projectBean = (ProjectBean) facesContext.getELContext().getELResolver().getValue(facesContext.getELContext(), null, "projectBean");
        projectService.createOrUpdateProject(projectBean.getAlias(), projectBean.getName(), projectBean.getDescription(), projectBean.getManager());
    }

    public List<ProjectBean> getProjects() {
        return projectService.getProjects().stream().map(ProjectBean::new).collect(Collectors.toList());
    }

    public List<Employee> getEmployees() {
        return orgStructureService.getEmployees();
    }

    public String remove() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ProjectBean projectBean = (ProjectBean) facesContext.getELContext().getELResolver().getValue(facesContext.getELContext(), null, "projectBean");
        Project project = projectService.getProject(projectBean.getAlias());
        try {
            projectService.deleteProject(project);
        } catch (VetoProjectDeletionException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return "";
        }
        return "viewprojects";
    }
}
