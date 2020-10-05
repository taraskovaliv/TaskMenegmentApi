package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.TasksApiService;
import com.kovaliv.api.model.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TasksApiServiceImpl extends TasksApiService {
    private final com.kovaliv.services.Service<com.kovaliv.models.Task> taskService;
    private final ModelMapper modelMapper;

    @Override
    public Response getAllTask(SecurityContext securityContext) {
        return Response.ok(taskService.getAll(com.kovaliv.models.Task.class)
                .stream()
                .map(t -> modelMapper.map(t, Task.class))
                .collect(Collectors.toList())
        ).build();
    }
}
