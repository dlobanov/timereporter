package ru.dlobanov.timereporter.model.impl;

import ru.dlobanov.timereporter.model.EmployeeRole;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "public", name = "employeerole")
public class EmployeeRoleImpl implements EmployeeRole, Serializable, Comparable<EmployeeRoleImpl> {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Size(min = 1, max = 255, message = "Role name length should be [min = 1, max = 255]")
    private String roleName;
    
    @ManyToMany(mappedBy = "roles")
    private Set<EmployeeImpl> employees;
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int compareTo(EmployeeRoleImpl o) {
        return getRoleName().compareTo(o.getRoleName());
    }
}