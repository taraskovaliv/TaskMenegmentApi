package com.kovaliv.services;

import com.kovaliv.models.Column;
import com.kovaliv.models.Task;
import com.kovaliv.repos.Repo;

public class TaskService extends Service<Task> {
    private final Repo<Column> columnRepo;
    private final Repo<Task> taskRepo;

    public TaskService() {
        taskRepo = new Repo<>();
        columnRepo = new Repo<>();
    }

    public void moveTaskToColumn(Integer taskId, Integer columnId) {
        Task task = taskRepo.getById(Task.class, taskId);
        Column column = columnRepo.getById(Column.class, columnId);
        task.setColumn(column);
        repo.update(task);
    }
}
