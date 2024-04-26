package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kensyu.CorrectAnswersBean;
import kensyu.CorrectAnswersDao;

class CorrectAnswersDaoTest {
	
	@Test
	@DisplayName("search_questions_idメソッドに、登録されていないquestionIdを引数として渡した場合、レコードが取得できないこと")
	public void notGetRecord() {
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
	@DisplayName("search_questions_idメソッドに、登録されているquestionIdを引数として渡した場合、レコードを取得できること")
	public void getRecord() {
		try {
			CorrectAnswersDao dao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = dao.search_questions_id(1);
			
			assertThat(ansList.get(0).getId(), is(1));
			assertThat(ansList.get(0).getAnswer(), is("aa"));
			assertThat(ansList.get(0).getQuestionsId(), is(1));
			assertThat(ansList.get(1).getId(), is(2));
			assertThat(ansList.get(1).getAnswer(), is("bb"));
			assertThat(ansList.get(1).getQuestionsId(), is(1));
			assertThat(ansList.get(2).getId(), is(3));
			assertThat(ansList.get(2).getAnswer(), is("cc"));
			assertThat(ansList.get(2).getQuestionsId(), is(1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("findAllメソッドで、全ての答えが取得できること")
	public void getAllAnswers() {
		try {
			CorrectAnswersDao dao = new CorrectAnswersDao();
			ArrayList<CorrectAnswersBean> ansList = dao.findAll();
			
			assertThat(ansList.get(0).getId(), is(1));
			assertThat(ansList.get(0).getAnswer(), is("aa"));
			assertThat(ansList.get(1).getId(), is(2));
			assertThat(ansList.get(1).getAnswer(), is("bb"));
			assertThat(ansList.get(2).getId(), is(3));
			assertThat(ansList.get(2).getAnswer(), is("cc"));
			assertThat(ansList.get(3).getId(), is(4));
			assertThat(ansList.get(3).getAnswer(), is("AA"));
			assertThat(ansList.get(4).getId(), is(5));
			assertThat(ansList.get(4).getAnswer(), is("BB"));
			assertThat(ansList.get(5).getId(), is(6));
			assertThat(ansList.get(5).getAnswer(), is("CC"));
			assertThat(ansList.get(6).getId(), is(7));
			assertThat(ansList.get(6).getAnswer(), is("あ"));
			assertThat(ansList.get(7).getId(), is(8));
			assertThat(ansList.get(7).getAnswer(), is("い"));
			assertThat(ansList.get(8).getId(), is(9));
			assertThat(ansList.get(8).getAnswer(), is("う"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
