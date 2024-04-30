package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import kensyu.QuestionsBean;
import kensyu.QuestionsDao;

@TestMethodOrder(OrderAnnotation.class) 
class QuestionsDaoTest {
	
	@Test
	@Order(1)
	@DisplayName("register_questionメソッドで、問題が登録できること")
	public void registerQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = dao.findAll();
			String question = "testtest";
			dao.register_question(question);
			String question2 = "testtest2";
			dao.register_question(question2);
			String question3 = "testtest3";
			dao.register_question(question3);
			ArrayList<QuestionsBean> queList2 = dao.findAll();
			
			assertThat(queList2.size() - queList.size(), is(3));
			assertThat(queList2.get(queList2.size()-3).getQuestion(), is("testtest"));
			assertThat(queList2.get(queList2.size()-2).getQuestion(), is("testtest2"));
			assertThat(queList2.get(queList2.size()-1).getQuestion(), is("testtest3"));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("search_idメソッドに、登録されていないquestionIdを引数として渡した場合、問題が取得できないこと")
	public void notGetQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			QuestionsBean question = dao.search_id(4);
			
			assertThat(question.getId(), is(0));
			assertNull(question.getQuestion());
		} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	@DisplayName("search_idメソッドに、登録されているquestionIdを引数として渡した場合、問題が取得できること")
	public void getQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = dao.findAll();
			int questionId = queList.get(queList.size() - 1).getId();
			QuestionsBean question = dao.search_id(questionId);
	
			assertThat(question.getQuestion(), is("testtest3"));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	@DisplayName("register_questionメソッドで、登録されていないquestionIdを引数として渡した場合、問題が更新できないこと")
	public void notUpdateQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			String question = "テストテスト";
			dao.update_question(question, 10);
			ArrayList<QuestionsBean> queList = dao.findAll();
			
			assertThat(queList, hasItems(not("テストテスト")));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
	@DisplayName("register_questionメソッドで、登録されているquestionIdを引数として渡した場合、問題が更新できること")
	public void updateQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = dao.findAll();
			String question = "テストテスト";
			dao.update_question(question, queList.get(queList.size() - 1).getId());
			ArrayList<QuestionsBean> queList2 = dao.findAll();
			
			assertThat(queList2.get(queList.size() - 1).getQuestion(), is("テストテスト"));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(6)
	@DisplayName("delete_questionメソッドで、登録されていないquestionIdを引数として渡した場合、問題の削除ができないこと")
	public void notDeleteQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = dao.findAll();
			dao.delete_question(10);
			ArrayList<QuestionsBean> queList2 = dao.findAll();
			
			assertThat(queList.size() - queList2.size(), is(not(1)));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(7)
	@DisplayName("delete_questionメソッドで、登録されているquestionIdを引数として渡した場合、問題の削除ができること")
	public void deleteQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = dao.findAll();
			int questionId1 = queList.get(queList.size() - 1).getId();
			dao.delete_question(questionId1);
			int questionId2 = queList.get(queList.size() - 2).getId();
			dao.delete_question(questionId2);
			int questionId3 = queList.get(queList.size() - 3).getId();
			dao.delete_question(questionId3);
			ArrayList<QuestionsBean> queList2 = dao.findAll();
			
			assertThat(queList.size() - queList2.size(), is(3));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
