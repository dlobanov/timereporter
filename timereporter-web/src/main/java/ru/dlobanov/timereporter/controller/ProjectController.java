package ru.dlobanov.timereporter.controller;

import ru.dlobanov.timereporter.OrgStructureService;
import ru.dlobanov.timereporter.controller.beans.ProjectBean;
import ru.dlobanov.timereporter.model.Employee;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ProjectController implements Serializable {

    @EJB
    private OrgStructureService orgStructureService;

    @Inject
    ProjectBean projectBean;

    public void createOrUpdateProject(ActionEvent event) {
//        ProjectBean projectBean = (ProjectBean) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("projectBean");
        orgStructureService.createOrUpdateProject(projectBean.getAlias(), projectBean.getName(), projectBean.getDescription(), projectBean.getManager());
    }

    public List<ProjectBean> getProjects() {
        return orgStructureService.getProjects().stream().map(ProjectBean::new).collect(Collectors.toList());
    }

    public List<Employee> getEmployees() {
        return orgStructureService.getEmployees();
    }
}
