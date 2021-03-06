package com.kovaliv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer taskId;

    @Column(name = "header", nullable = false, length = 100)
    private String header;

    @Column(name = "text", nullable = false, length = 500)
    private String text;

    @ManyToOne
    private com.kovaliv.models.Column column;

    @OneToMany(mappedBy = "task")
    private List<Comment> comment;
}
