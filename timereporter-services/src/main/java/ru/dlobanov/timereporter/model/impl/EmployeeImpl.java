package ru.dlobanov.timereporter.model.impl;

import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.EmployeeRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(schema = "timereporter", name = "employee")
public class EmployeeImpl implements Employee {

    @Id
    @Size(min = 1, max = 255, message = "Login length should be [min = 1, max = 255]")
    private String login;

    @NotNull
    @Size(min = 1, max = 255, message = "Password length should be [min = 1, max = 255]")
    private String password;

    @NotNull
    @Size(min = 1, max = 255, message = "Name length should be [min = 1, max = 255]")
    private String name;

    @NotNull
    @Size(min = 1, max = 255, message = "Email length should be [min = 1, max = 255]")
    private String email;

    @OneToMany(mappedBy = "employee")
    private List<WeeklyReportImpl> reports;

    @ManyToOne
    private OrgUnitImpl unit;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "timereporter", name = "employee_role_map", joinColumns = @JoinColumn(name = "employee_login", referencedColumnName = "login"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "rolename"))
    private Set<EmployeeRoleImpl> roles = new HashSet<>();

    public OrgUnitImpl getUnit() {
        return unit;
    }

    public void setUnit(OrgUnitImpl unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<WeeklyReportImpl> getReports() {
        return reports;
    }

    public void setReports(List<WeeklyReportImpl> reports) {
        this.reports = reports;
    }

    public void addReport(WeeklyReportImpl report) {
        if (reports == null) {
            reports = new ArrayList<>();
        }
        reports.add(report);
        report.setEmployee(this);
    }

    public List<EmployeeRole> getRoles() {
        List<EmployeeRole> list = new ArrayList<>(roles.size());
        list.addAll(roles);
//        Collections.sort(list);
        return list;
    }
    
    public void addRole(EmployeeRoleImpl role) {
        roles.add(role);
    }
}
