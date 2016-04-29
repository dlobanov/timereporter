package ru.dlobanov.timereporter.rest;

import ru.dlobanov.timereporter.ProjectService;
import ru.dlobanov.timereporter.model.Project;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/projects")
public class ProjectResource {

    @EJB
    private ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ProjectManager"})
    public Response getProjects(@Context SecurityContext securityContext, @Context HttpServletRequest httpServletRequest) {
        ProjectInfos projects = fromProjects(projectService.getProjects());
        return Response.ok(projects).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ProjectManager"})
    public Response createProject(ProjectInfo info) {
        Project created = projectService.createOrUpdateProject(info.alias, info.name, info.description, info.manager);
        return Response.status(Response.Status.CREATED).entity(new ProjectInfo(created)).build();
    }

    private ProjectInfos fromProjects(List<Project> projects) {
        ProjectInfos infos = new ProjectInfos();
        for (Project project : projects) {
            infos.projects.add(new ProjectInfo(project));
        }
        return infos;
    }
}
