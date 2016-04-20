package ru.dlobanov.timereporter.model.impl;

import ru.dlobanov.timereporter.model.OrgUnit;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "unit")
public class OrgUnitImpl implements OrgUnit, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(min = 1, max = 255, message = "Name length should be [min = 1, max = 255]")
    private String name;

    @Size(max = 4000, message = "Description max lenght is 4000 characters")
    private String description;

    @OneToOne
    private EmployeeImpl manager;
    
    @ManyToOne
    private ProjectImpl project;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "unit")
    private List<EmployeeImpl> employees;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProjectImpl getProject() {
        return project;
    }
    
    public List<EmployeeImpl> getEmployees() {
        return employees;
    }
    
    public EmployeeImpl getManager() {
        return manager;
    }
    
    public void setManager(EmployeeImpl manager) {
        this.manager = manager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProject(ProjectImpl project) {
        this.project = project;
    }
    
    public void setEmployees(List<EmployeeImpl> employees) {
        this.employees = employees;
    }
    
    public void addEmployee(EmployeeImpl employee) {
        if (employees == null) {
            employees = new ArrayList<EmployeeImpl>();
        }
        employees.add(employee);
        employee.setUnit(this);
    }
}
