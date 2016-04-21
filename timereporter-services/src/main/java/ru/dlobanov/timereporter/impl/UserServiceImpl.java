package ru.dlobanov.timereporter.impl;

import ru.dlobanov.timereporter.UserService;
import ru.dlobanov.timereporter.model.Employee;
import ru.dlobanov.timereporter.model.impl.EmployeeImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserServiceImpl implements UserService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Employee findEmployee(String login) {
        return entityManager.find(EmployeeImpl.class, login);
    }

}
