package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kensyu.QuestionsBean;
import kensyu.QuestionsDao;

class QuestionsDaoTest {
	
	@Test
	@DisplayName("search_idメソッドに、登録されていないquestionIdを引数として渡した場合、レコードが取得できないこと")
	public void notGetRecord() {
		try {
			QuestionsDao dao = new QuestionsDao();
			QuestionsBean question = dao.search_id(10);
			assertThat(question.getId(), is(0));
			assertNull(question.getQuestion());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("search_idメソッドに、登録されているquestionIdを引数として渡した場合、レコードを取得できること")
	public void getRecord() {
		try {
			QuestionsDao dao = new QuestionsDao();
			QuestionsBean question = dao.search_id(1);
			assertThat(question.getId(), is(1));
			assertThat(question.getQuestion(), is("test"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("findAllメソッドで、全ての問題が取得できること")
	public void getAllQuestion() {
		try {
			QuestionsDao dao = new QuestionsDao();
			ArrayList<QuestionsBean> queList = dao.findAll();
			
			assertThat(queList.get(0).getId(), is(1));
			assertThat(queList.get(0).getQuestion(), is("test"));
			assertThat(queList.get(1).getId(), is(2));
			assertThat(queList.get(1).getQuestion(), is("test2"));
			assertThat(queList.get(2).getId(), is(3));
			assertThat(queList.get(2).getQuestion(), is("test3"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
