package ru.dlobanov.timereporter.impl;

import ru.dlobanov.timereporter.OrgStructureService;
import ru.dlobanov.timereporter.ProjectDeletionEvent;
import ru.dlobanov.timereporter.ProjectService;
import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.EmployeeRole;
import ru.dlobanov.timereporter.model.OrgUnit;
import ru.dlobanov.timereporter.model.Project;
import ru.dlobanov.timereporter.model.impl.EmployeeImpl;
import ru.dlobanov.timereporter.model.impl.OrgUnitImpl;
import ru.dlobanov.timereporter.model.impl.ProjectImpl;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrgStructureServiceImpl implements OrgStructureService, ProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Event<ProjectDeletionEvent> projectDeletionEventEvent;

    @Override
    public Project createOrUpdateProject(String alias, String name, String description, String manager) {
        ProjectImpl project = entityManager.find(ProjectImpl.class, alias);
        if (project == null) {
            project = new ProjectImpl();
            project.setAlias(alias);
        }
        project.setName(name);
        project.setDescription(description);
        project.setManager(manager == null ? null : entityManager.find(EmployeeImpl.class, manager));
        entityManager.persist(project);
        return project;
    }

    @Asynchronous
    @Override
    public void deleteProject(Project project) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
        ProjectImpl projectImpl = entityManager.find(ProjectImpl.class, project.getAlias());
        if (projectImpl != null) {
            projectDeletionEventEvent.fire(new ProjectDeletionEvent(projectImpl));
            entityManager.remove(projectImpl);
        }
    }

    public OrgUnit createNewUnit(String name, String description, ProjectImpl project) {
        OrgUnitImpl unit = new OrgUnitImpl();
        unit.setName(name);
        unit.setDescription(description);
        unit.setProject(project);
        entityManager.persist(project);
        return unit;
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjects() {
        return entityManager.createQuery("FROM ProjectImpl").getResultList();
    }
    
    public Project getProject(String alias) {
        return entityManager.find(ProjectImpl.class, alias);
    }

    public List<OrgUnit> getUnits(Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<EmployeeRole> getRoles() {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getEmployees() {
        return entityManager.createQuery("FROM EmployeeImpl").getResultList();
    }

    public EmployeeRole addRole(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public Employee addEmployee(String login, String password, String email) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setManagerToProject(Employee manager, Project project) {
        // TODO Auto-generated method stub

    }

    public void setManagerToUnit(Employee manager, OrgUnit unit) {
        // TODO Auto-generated method stub

    }

    public void changeUnit(Employee employee, OrgUnit unit) {
        // TODO Auto-generated method stub

    }

    @Override
    public OrgUnit createNewUnit(String name, String description, Project project) {
        return null;
    }


}
