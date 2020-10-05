package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.TaskApiService;
import com.kovaliv.api.model.Task;
import com.kovaliv.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Service
@RequiredArgsConstructor
public class TaskApiServiceImpl extends TaskApiService {
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @Override
    public Response createTask(Task task, SecurityContext securityContext) {
        return Response.status(Response.Status.CREATED).entity(modelMapper.map(
                taskService.save(modelMapper.map(task, com.kovaliv.models.Task.class)),
                Task.class)
        ).build();
    }

    @Override
    public Response deleteTask(Task task, SecurityContext securityContext) {
        taskService.delete(modelMapper.map(task, com.kovaliv.models.Task.class));
        return Response.ok().build();
    }

    @Override
    public Response editTask(Task task, SecurityContext securityContext) {
        taskService.update(modelMapper.map(task, com.kovaliv.models.Task.class));
        return Response.ok().build();
    }

    @Override
    public Response getTaskById(@Min(1) Integer taskId, SecurityContext securityContext) {
        return Response.ok(modelMapper.map(
                taskService.getById(com.kovaliv.models.Task.class, taskId),
                Task.class)
        ).build();
    }

    @Override
    public Response moveTaskToColumn(@NotNull Integer taskId, @NotNull Integer columnId, SecurityContext securityContext) {
        taskService.moveTaskToColumn(taskId, columnId);
        return Response.ok().build();
    }
}