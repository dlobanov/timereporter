package ru.dlobanov.timereporter.model.impl;

import ru.dlobanov.timereporter.model.Project;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "timereporter", name = "project")
public class ProjectImpl implements Project, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(min = 1, max = 32, message = "Alias length should be [min = 1, max = 32]")
    private String alias;

    @NotNull
    @Size(min = 1, max = 255, message = "Name length should be [min = 1, max = 255]")
    private String name;

    @Size(max = 4000, message = "Description max lenght is 4000 characters")
    private String description;
    
    @OneToOne
    private EmployeeImpl manager;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="project")
    private List<OrgUnitImpl> units;

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<OrgUnitImpl> getUnits() {
        return units;
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

    public void setUnits(List<OrgUnitImpl> units) {
        this.units = units;
    }

    public void addUnit(OrgUnitImpl unit) {
        if (units == null) {
            units = new ArrayList<OrgUnitImpl>();
        }
        units.add(unit);
        unit.setProject(this);
    }
    
    public EmployeeImpl getManager() {
        return manager;
    }
    
    public void setManager(EmployeeImpl manager) {
        this.manager = manager;
    }
}
