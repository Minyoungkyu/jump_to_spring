package com.sbs.sbb.answer;


import com.sbs.sbb.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Answer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT") // "내용" 처럼 글자 수를 제한할 수 없을 때 사용
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne // 질문 한개에 답변 여러개 ( N : 1 관계 ) Question 이 부모, Answer 가 자식
    private Question question;


}
