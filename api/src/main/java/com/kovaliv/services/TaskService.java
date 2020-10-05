package com.kovaliv.services;

import com.kovaliv.aspect.CountTime;
import com.kovaliv.models.Column;
import com.kovaliv.models.Task;
import com.kovaliv.repos.Repo;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TaskService extends Service<Task> {
    private final Repo<Column> columnRepo;
    private final Repo<Task> taskRepo;

    @Autowired
    public TaskService(Repo<Column> columnRepo, Repo<Task> taskRepo) {
        super(taskRepo);
        this.columnRepo = columnRepo;
        this.taskRepo = taskRepo;
    }

    @CountTime
    public void moveTaskToColumn(Integer taskId, Integer columnId) {
        Task task = taskRepo.getById(Task.class, taskId);
        Column column = columnRepo.getById(Column.class, columnId);
        task.setColumn(column);
        repo.update(task);
    }
}
