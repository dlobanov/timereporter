package ru.dlobanov.timereporter.controller;

import ru.dlobanov.timereporter.UserService;
import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.EmployeeRole;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;

@Named
@SessionScoped
public class UserData implements Serializable {

    @Inject
    private Principal userPrincipal;

    @Inject
    private UserService userService;
    
    public String getUsername() {
        return findCurrentUser().getName();
    }
    
    public String getProject() {
        Employee currentUser = findCurrentUser();
        if (currentUser.getUnit() != null) {
            return currentUser.getUnit().getProject().getName();
        }
        return "";
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
