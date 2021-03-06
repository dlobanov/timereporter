package ru.dlobanov.timereporter;

import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.EmployeeRole;
import ru.dlobanov.timereporter.model.OrgUnit;
import ru.dlobanov.timereporter.model.Project;

import java.util.List;

public interface OrgStructureService {

    OrgUnit createNewUnit(String name, String description, Project project);

    List<OrgUnit> getUnits(Project project);

    List<EmployeeRole> getRoles();

    List<Employee> getEmployees();

    EmployeeRole addRole(String name);

    Employee addEmployee(String login, String password, String email);

    void setManagerToProject(Employee manager, Project project);

    void setManagerToUnit(Employee manager, OrgUnit unit);

    void changeUnit(Employee employee, OrgUnit unit);
}
