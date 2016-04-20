package ru.dlobanov.timereporter;

import ru.dlobanov.timereporter.model.Employee;

public interface UserService {

    Employee findEmployee(String login);
    
}
