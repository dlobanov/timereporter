package ru.dlobanov.timereporter.controller;

import ru.dlobanov.timereporter.UserService;
import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.EmployeeRole;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;

@Named
public class UserData implements Serializable {
    
    private Principal userPrincipal;

    @EJB
    private UserService userService;
    
    public UserData() {
        userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }
    
    public String getUsername() {
        return findCurrentUser().getName();
    }
    
    public String getProject() {
        return findCurrentUser().getUnit().getProject().getName();
    }

    public String getUnit() {
        return findCurrentUser().getUnit().getName();
    }
    
    public String getRoles() {
        List<EmployeeRole> roles = findCurrentUser().getRoles();
        StringBuilder result = new StringBuilder();
        Iterator<EmployeeRole> iterator = roles.iterator();
        while (iterator.hasNext()) {
            result.append(iterator.next().getRoleName());
            if (iterator.hasNext()) {
                result.append(",");
            }
        }
        return result.toString();
    }

    private Employee findCurrentUser() {
        return userService.findEmployee(userPrincipal.getName());
    }
}
