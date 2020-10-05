package com.kovaliv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "columns")
public class Column {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "ID", unique = true, nullable = false)
    private Integer columnId;

    @javax.persistence.Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "column")
    private List<Task> tasks;
}
