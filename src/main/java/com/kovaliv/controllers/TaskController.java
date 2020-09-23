package com.kovaliv.controllers;

import com.kovaliv.models.Task;
import com.kovaliv.services.TaskService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    private final TaskService taskService;

    public TaskController() {
        taskService = new TaskService();
    }

    @GET
    public Response getTask(Integer id) {
        return Response.ok(taskService.getById(Task.class, id)).build();
    }

    @GET
    @Path("/all")
    public Response getTasks() {
        return Response.ok(taskService.getAll(Task.class)).build();
    }

    @POST
    public Response addTask(Task task) {
        taskService.save(task);
        return Response.ok().build();
    }

    @PATCH
    public Response moveTaskToColumn(Integer taskId, Integer columnId) {
        taskService.moveTaskToColumn(taskId, columnId);
        return Response.ok().build();
    }

    @PUT
    public Response updateTask(Task task) {
        taskService.update(task);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteTask(Task task) {
        taskService.delete(task);
        return Response.ok().build();
    }
}
