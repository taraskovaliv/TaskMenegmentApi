package com.kovaliv.models;

import com.kovaliv.security.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.ZonedDateTimeType;

import javax.persistence.Column;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer commentId;

    @Column(nullable = false, length = 100)
    private String text;

    private ZonedDateTimeType createdTime;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User author;
}
