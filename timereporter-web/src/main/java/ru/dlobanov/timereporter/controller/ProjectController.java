package ru.dlobanov.timereporter.controller;

import ru.dlobanov.timereporter.OrgStructureService;
import ru.dlobanov.timereporter.controller.beans.ProjectBean;
import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.Project;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ProjectController {

    @EJB
    private OrgStructureService orgStructureService;

    public void createOrUpdateProject(ActionEvent event) {
        ProjectBean projectBean = (ProjectBean) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("projectBean");
        orgStructureService.createOrUpdateProject(projectBean.getAlias(), projectBean.getName(), projectBean.getDescription(), projectBean.getManager());
    }
    
    public List<ProjectBean> getProjects() {
        List<Project> projects = orgStructureService.getProjects();
        List<ProjectBean> result = new ArrayList<ProjectBean>(projects.size());
        for (Project project : projects) {
            result.add(new ProjectBean(project.getAlias(), project.getName(), project.getDescription()));
        }
        return result;
    }
    
    public List<Employee> getEmployees() {
        return orgStructureService.getEmployees();
    }
}
