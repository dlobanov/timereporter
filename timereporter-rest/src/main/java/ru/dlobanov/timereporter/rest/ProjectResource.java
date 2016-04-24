package ru.dlobanov.timereporter.rest;

import ru.dlobanov.timereporter.OrgStructureService;
import ru.dlobanov.timereporter.model.EmployeeRole;
import ru.dlobanov.timereporter.model.Project;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/projects")
public class ProjectResource {

    @EJB
    private OrgStructureService orgStructureService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ProjectManager"})
    public Response getProjects(@Context SecurityContext securityContext, @Context HttpServletRequest httpServletRequest) {
        ProjectInfos projects = fromProjects(orgStructureService.getProjects());
        return Response.ok(projects).build();
    }

    private ProjectInfos fromProjects(List<Project> projects) {
        ProjectInfos infos = new ProjectInfos();
        for (Project project : projects) {
            infos.projects.add(new ProjectInfo(project));
        }
        return infos;
    }
}
