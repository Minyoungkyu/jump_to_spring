package com.sbs.sbb;

import com.sbs.sbb.answer.Answer;
import com.sbs.sbb.answer.AnswerRepository;
import com.sbs.sbb.question.Question;
import com.sbs.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa() {

		Question q1 = new Question();
		q1.setSubject("ssb가 무엇인가요?");
		q1.setContent("ssb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}

	@Test
	void testJpa2() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size()	);

		Question q = all.get(0);
		assertEquals("ssb가 무엇인가요?", q.getSubject());
	}

	@Test
	void testJpa3() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("ssb가 무엇인가요?", q.getSubject());
		}
	}

	@Test
	void testJpa4() {
		Question q = this.questionRepository.findBySubject("ssb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	@Test
	void testJpa5() {
		Question q = this.questionRepository.findBySubjectAndContent("ssb가 무엇인가요?", "ssb에 대해서 알고 싶습니다.");
		assertEquals(1,  q.getId());
	}

	@Test
	void testJpa6() {
		List<Question> qList = this.questionRepository.findBySubjectLike("ssb%");
		Question q = qList.get(0);
		assertEquals("ssb가 무엇인가요?", q.getSubject());
	}

	@Test
	void testJpa7() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}

	@Test
	void testJpa8() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}

	@Test
	void testJpa9() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		this.answerRepository.save(a);

	}

	@Test
	void testJpa10() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}

	@Transactional // Transactional 은 함수가 종료 될 때 까지 DB 세션을 종료시키지 않음.
	@Test
	void testJpa11() {
		Optional<Question> oq = this.questionRepository.findById(2);// finbyId 후에 DB세션 종료 ( 만약, 이때 answerList 까지 가져오는건 (Eager 방식)
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList(); // answerList 는 처음에 가져오는게 아니라 이때 다시 DB에서 찾아온다. (LAZY 방식)

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}



}
