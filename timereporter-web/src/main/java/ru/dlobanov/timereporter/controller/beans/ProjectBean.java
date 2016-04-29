package ru.dlobanov.timereporter.controller.beans;

import ru.dlobanov.timereporter.ProjectService;
import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.Project;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named("projectBean")
@RequestScoped
public class ProjectBean implements Serializable {

    @NotNull(message = "{field.required}")
    @Size(min = 1, max = 255, message = "{field.length.constraint}")
    private String name;

    @NotNull(message = "{required.field}")
    @Size(min = 1, max = 32, message = "{field.length.constraint}")
    private String alias;

    @Size(max = 4000, message = "{field.length.constraint}")
    private String description;

    @NotNull(message = "{field.required}")
    private String manager;
    
    @Inject
    private ProjectService orgStructureService;
    
    @PostConstruct
    public void init() {
        String projectAlias = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("project");
        if (projectAlias != null) {
            Project project = orgStructureService.getProject(projectAlias);
            this.alias = project.getAlias();
            this.name = project.getName();
            this.description = project.getDescription();
            this.manager = project.getManager().map(Employee::getLogin).orElse(null);
        }
    }
    
    //Left intentionally empty for JSF engine 
    public ProjectBean() {
    }

    public ProjectBean(Project project) {
        this(project.getAlias(), project.getName(), project.getDescription());
        this.manager = project.getManager().map(Employee::getLogin).orElse(null);
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

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
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

}
