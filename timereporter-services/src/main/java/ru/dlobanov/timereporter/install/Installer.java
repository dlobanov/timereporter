package ru.dlobanov.timereporter.install;

import org.jboss.security.auth.spi.Util;
import ru.dlobanov.timereporter.model.impl.EmployeeImpl;
import ru.dlobanov.timereporter.model.impl.EmployeeRoleImpl;
import ru.dlobanov.timereporter.model.impl.OrgUnitImpl;
import ru.dlobanov.timereporter.model.impl.ProjectImpl;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class Installer {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void atStartup() {
        System.out.println("startUp");
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        ProjectImpl project = findOrCreateProject("ALU", "Alcatel");
        findOrCreateProject("Elster", "Elster/Jupiter");
        OrgUnitImpl unit = findOrCreateUnit("WPS", project);
        EmployeeRoleImpl simpleWorker = findOrCreateRole("SimpleWorker");
        EmployeeRoleImpl unitManager = findOrCreateRole("UnitManager");
        EmployeeRoleImpl projectManager = findOrCreateRole("ProjectManager");
        EmployeeImpl worker = findOrCreateEmployee("dlobanov", unit);
        worker.addRole(projectManager);
        entityManager.persist(worker);
    }

    private EmployeeRoleImpl findOrCreateRole(String role) {
        EmployeeRoleImpl employeeRole = new EmployeeRoleImpl();

        employeeRole.setRoleName(role);

        if (entityManager.find(EmployeeRoleImpl.class, employeeRole.getRoleName()) == null) {
            entityManager.persist(employeeRole);
        }

        return employeeRole;
    }

    private EmployeeImpl findOrCreateEmployee(String login, OrgUnitImpl unit) {
        EmployeeImpl employee = entityManager.find(EmployeeImpl.class, login);
        if (employee != null) {
            return employee;
        }
        employee = new EmployeeImpl();
        employee.setLogin(login);
        employee.setEmail(login + "@mera.ru");
        employee.setPassword(Util.createPasswordHash("SHA-256", Util.BASE64_ENCODING, null, null, "dlobanov"));
        employee.setName(login);
        employee.setUnit(unit);
        entityManager.persist(employee);
        return employee;
    }

    private OrgUnitImpl findOrCreateUnit(String name, ProjectImpl project) {
        OrgUnitImpl unit = entityManager.find(OrgUnitImpl.class, name);
        if (unit != null) {
            return unit;
        }
        unit = new OrgUnitImpl();
        unit.setName(name);
        unit.setProject(project);
        entityManager.persist(unit);
        return unit;
    }

    private ProjectImpl findOrCreateProject(String alias, String name) {
        ProjectImpl project = entityManager.find(ProjectImpl.class, alias);
        if (project != null) {
            return project;
        }
        project = new ProjectImpl();
        project.setAlias(alias);
        project.setName(name);
        entityManager.persist(project);
        return project;
    }

}
