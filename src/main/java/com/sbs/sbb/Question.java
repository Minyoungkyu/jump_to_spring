package com.sbs.sbb;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany( mappedBy = "question", cascade = CascadeType.REMOVE) // Question(질문) 을 삭제하면 밑에 answerList도 같이 삭제된다.
    private List<Answer> answerList;




}
