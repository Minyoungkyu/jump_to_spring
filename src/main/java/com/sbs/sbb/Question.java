package com.sbs.sbb;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;




}
