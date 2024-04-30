package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import kensyu.CorrectAnswersBean;
import kensyu.CorrectAnswersDao;
import kensyu.QuestionsBean;
import kensyu.QuestionsDao;

@TestMethodOrder(OrderAnnotation.class) 
class CorrectAnswersDaoTest {
	
	
	@BeforeAll
	@DisplayName("テスト用の問題データを作成")
	static void registerQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			String question = "test";
			dao.register_question(question);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@AfterAll
	@DisplayName("テスト用の問題データを削除")
	static void deleteQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = dao.findAll();
			dao.delete_question(queList.get(queList.size() - 1).getId());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	@DisplayName("register_answersメソッドで、答えが登録できること")
	public void registerAnswers() {
		try {
			QuestionsDao queDao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = queDao.findAll();
			
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			String[] answers = {"a", "b", "c"};
			ansDao.register_answers(queList.get(queList.size() - 1).getId(), answers);
			
			ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
			
			assertThat(ansList2.size() - ansList.size(), is(3));
			assertThat(ansList2.get(ansList2.size() -3).getAnswer(), is("a"));
			assertThat(ansList2.get(ansList2.size() -2).getAnswer(), is("b"));
			assertThat(ansList2.get(ansList2.size() -1).getAnswer(), is("c"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("search_questions_idメソッドに、登録されていないquestionIdを引数として渡した場合、答えが取得できないこと")
	public void notGetAnswers() {
		try {
			CorrectAnswersDao dao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> answers = dao.search_questions_id(10);
			
			for(CorrectAnswersBean answer : answers) {
				assertThat(answer.getId(), is(0));
				assertNull(answer.getAnswer());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	@DisplayName("search_questions_idメソッドに、登録されているquestionIdを引数として渡した場合、答えを取得できること")
	public void getAnswers() {
		try {
			QuestionsDao queDao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = queDao.findAll();
			
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = ansDao.search_questions_id(queList.get(queList.size() - 1).getId());
			
			assertThat(ansList.get(ansList.size() - 3).getAnswer(), is("a"));
			assertThat(ansList.get(ansList.size() - 2).getAnswer(), is("b"));
			assertThat(ansList.get(ansList.size() - 1).getAnswer(), is("c"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	@DisplayName("delete_answersメソッドで、登録されていないquestionIdを引数として渡した場合、答えが削除できないこと")
	public void notDeleteAnswers() {
		try {
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			ansDao.delete_answers(10);
			ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
			
			assertThat(ansList.size() - ansList2.size(), is(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
	@DisplayName("delete_answersメソッドで、登録されているquestionIdを引数として渡した場合、答えが削除できること")
	public void deleteAnswers() {
		try {
			QuestionsDao queDao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = queDao.findAll();
			
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			ansDao.delete_answers(queList.get(queList.size() - 1).getId());
			ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
			
			assertThat(ansList.size() - ansList2.size(), is(3));
			assertThat(ansList2.get(ansList2.size() - 1).getAnswer(), is(not("c")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(6)
	@DisplayName("register_answerメソッドで、答えが1件登録できること")
	public void registerAnswer() {
		try {
			QuestionsDao queDao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = queDao.findAll();
			
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			String answer = "A";
			ansDao.register_answer(queList.get(queList.size() - 1).getId(), answer);
			
			ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
			
			assertThat(ansList2.size() - ansList.size(), is(1));
			assertThat(ansList2.get(ansList2.size() -1).getAnswer(), is("A"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(7)
	@DisplayName("update_answerメソッドで、登録されているanswerIdを引数として渡した場合、答えが1件更新されること")
	public void updateAnswer() {
		try {	
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			String answer = "B";
			ansDao.update_answer(ansList.get(ansList.size() - 1).getId(), answer);
			ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
			
			assertThat(ansList2.get(ansList2.size() -1).getAnswer(), is("B"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(8)
	@DisplayName("update_answerメソッドで、登録されていないanswerIdを引数として渡した場合、答えが更新されないこと")
	public void notUpdateAnswer() {
		try {
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			String answer = "C";
			ansDao.update_answer(10, answer);
			ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
			
			assertThat(ansList2.get(ansList2.size() -1).getAnswer(), is(not("C")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(9)
	@DisplayName("delete_answerメソッドで、登録されていないanswerIdを引数として渡した場合、答えが削除できないこと")
	public void notDeleteAnswer() {
		try {
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			ansDao.delete_answer(10);
			ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
			
			assertThat(ansList.size() - ansList2.size(), is(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(10)
	@DisplayName("delete_answerメソッドで、登録されているanswerIdを引数として渡した場合、答えが1件削除できること")
	public void deleteAnswer() {
		try {
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			ansDao.delete_answer(ansList.get(ansList.size() - 1).getId());
			ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
			
			assertThat(ansList.size() - ansList2.size(), is(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
