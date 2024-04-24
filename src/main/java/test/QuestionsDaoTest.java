package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kensyu.QuestionsBean;
import kensyu.QuestionsDao;

class QuestionsDaoTest {
	
	@Test
	@DisplayName("search_idメソッドに、登録されていないquestionIdを引数として渡した場合、戻り値にidや問題文がセットされない")
	public void notSetField() {
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
	@DisplayName("search_idメソッドに、登録されているquestionIdを引数として渡した場合、戻り値にidや問題文がセットされる")
	public void setField() {
		try {
			QuestionsDao dao = new QuestionsDao();
			QuestionsBean question = dao.search_id(70);
			assertThat(question.getId(), is(70));
			assertNull(question.getQuestion(), is("test9"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
