package ru.dlobanov.timereporter.controller.beans;

import ru.dlobanov.timereporter.OrgStructureService;
import ru.dlobanov.timereporter.model.Project;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
public class ProjectBean {
    
    private String alias;
    
    private String name;
    
    private String description;
    
    private String manager;
    
    @Inject
    private OrgStructureService orgStructureService;
    
    @PostConstruct
    public void init() {
        String projectAlias = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("project");
        if (projectAlias != null) {
            Project project = orgStructureService.getProject(projectAlias);
            this.alias = project.getAlias();
            this.name = project.getName();
            this.description = project.getDescription();
            this.manager = project.getManager() == null ? null : project.getManager().getLogin();
        }
    }
    
    //Left intentionally empty for JSF engine 
    public ProjectBean() {
    }
    
    public ProjectBean(String alias, String name, String description) {
        super();
        this.alias = alias;
        this.name = name;
        this.description = description;
    }

    public String getAlias() {
        return alias;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getManager() {
        return manager;
    }
    
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    public void setOrgStructureService(OrgStructureService orgStructureService) {
        this.orgStructureService = orgStructureService;
    }
    
    public OrgStructureService getOrgStructureService() {
        return orgStructureService;
    }
}
