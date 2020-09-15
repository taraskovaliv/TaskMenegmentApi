package com.kovaliv.models;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer taskId;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "text", nullable = false)
    private String text;
}
