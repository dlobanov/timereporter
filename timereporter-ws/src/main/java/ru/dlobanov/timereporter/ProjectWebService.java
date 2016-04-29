package ru.dlobanov.timereporter;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@WebService
public class ProjectWebService {

    @WebMethod
    public String sayHello(String name) {
        return "Hello, " + name;
    }

}
