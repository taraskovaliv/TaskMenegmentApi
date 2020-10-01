package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.TasksApiService;
import com.kovaliv.api.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Service
@RequiredArgsConstructor
public class TaskApiServiceImpl extends TasksApiService {
    private final com.kovaliv.services.Service<Task> taskService;

    @Override
    public Response createTask(Task task, SecurityContext securityContext) {
        return Response.ok(taskService.save(task)).build();
    }

    @Override
    public Response deleteTask(Task task, SecurityContext securityContext) {
        taskService.delete(task);
        return Response.ok().build();
    }

    @Override
    public Response editTask(Task task, SecurityContext securityContext) {
        taskService.update(task);
        return Response.ok().build();
    }

    @Override
    public Response getAllTask(SecurityContext securityContext) {
        return Response.ok(taskService.getAll(Task.class)).build();
    }
}
