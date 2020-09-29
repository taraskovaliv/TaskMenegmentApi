package com.kovaliv.services;

import com.kovaliv.aspect.CountTime;
import com.kovaliv.models.Comment;
import com.kovaliv.models.Task;
import com.kovaliv.repos.Repo;
import com.kovaliv.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.ForbiddenException;

@Service
public class CommentService extends com.kovaliv.services.Service<Comment> {
    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public CommentService(Repo<Comment> repo, TaskService taskService, UserService userService) {
        super(repo);
        this.taskService = taskService;
        this.userService = userService;
    }

    @CountTime
    public Comment save(String token, Comment comment) {
        comment.setAuthor(userService.getByToken(token));
        comment.setTask(taskService.getById(Task.class, comment.getTask().getTaskId()));
        return save(comment);
    }

    public void update(String token, Comment comment) {
        if (!isAuthor(token, comment)) {
            throw new ForbiddenException("You can`t change this comment");
        }
        update(comment);
    }

    public void delete(String token, Comment comment) {
        if (!isAuthor(token, comment)) {
            throw new ForbiddenException("You can`t delete this comment");
        }
        delete(comment);
    }

    private boolean isAuthor(String token, Comment comment) {
        return getById(Comment.class, comment.getCommentId())
                .getAuthor()
                .getLogin()
                .equals(userService
                        .getByToken(token)
                        .getLogin());
    }
}
