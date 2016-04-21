package ru.dlobanov.timereporter.rest;

import ru.dlobanov.timereporter.OrgStructureService;
import ru.dlobanov.timereporter.model.Project;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/projects")
public class ProjectResource {

    @EJB
    private OrgStructureService orgStructureService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjects() {
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
