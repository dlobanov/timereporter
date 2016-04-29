package ru.dlobanov.timereporter.model.impl;

import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.OrgUnit;
import ru.dlobanov.timereporter.model.Project;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(schema = "timereporter", name = "project")
public class ProjectImpl implements Project, Serializable {

    @Id
    @Size(min = 1, max = 32, message = "Alias length should be [min = {min}, max = {max}]")
    private String alias;

    @NotNull
    @Size(min = 1, max = 255, message = "Name length should be [min = {min}, max = {max}]")
    private String name;

    @Size(max = 4000, message = "Description max length is {max} characters")
    private String description;

    @OneToOne(optional = true, cascade = CascadeType.DETACH)
    @JoinColumn(name = "manager")
    private EmployeeImpl manager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<OrgUnitImpl> units;

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Optional<Employee> getManager() {
        return Optional.ofNullable(manager);
    }

    @Override
    public List<? extends OrgUnit> getUnits() {
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

    public void setManager(EmployeeImpl manager) {
        this.manager = manager;
    }

    public void setUnits(List<OrgUnitImpl> units) {
        this.units = units;
    }

    public void addUnit(OrgUnitImpl unit) {
        if (units == null) {
            units = new ArrayList<>();
        }
        units.add(unit);
        unit.setProject(this);
    }

}
