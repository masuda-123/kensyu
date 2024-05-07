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
	static void registerQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		String question = "test";
		dao.register_question(question);
	}
	
	@AfterAll
	@DisplayName("テスト用の問題データを削除")
	static void deleteQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		ArrayList<QuestionsBean> queList = dao.findAll();
		dao.delete_question(queList.get(queList.size() - 1).getId());
	}
	
	@Test
	@Order(1)
	@DisplayName("register_answersメソッドで、答えが登録できること")
	public void registerAnswers() throws Exception {
		QuestionsDao queDao = new QuestionsDao();
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = queDao.findAll();
		
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		//登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
		String[] answers = {"a", "b", "c"};
		//登録されているquestions_idを渡して、答えを登録
		ansDao.register_answers(queList.get(queList.size() - 1).getId(), answers);
		
		//登録後再度、登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
		
		//登録前と登録後で、答えが3つ増えていることを確認
		assertThat(ansList2.size() - ansList.size(), is(3));
		
		//最新のデータが、先程登録した答えかを確認
		assertThat(ansList2.get(ansList2.size() -3).getAnswer(), is("a"));
		assertThat(ansList2.get(ansList2.size() -2).getAnswer(), is("b"));
		assertThat(ansList2.get(ansList2.size() -1).getAnswer(), is("c"));
	}
	
	@Test
	@Order(2)
	@DisplayName("search_questions_idメソッドに、登録されていないquestionIdを引数として渡した場合、答えが取得できないこと")
	public void notGetAnswers() throws Exception{
		CorrectAnswersDao dao = new CorrectAnswersDao();
		//登録されていないquestions_idを渡して、それと一致する答えを取得しようとする
		ArrayList<CorrectAnswersBean> answers = dao.search_questions_id(10);
		
		//答えが取得できないことを確認
		for(CorrectAnswersBean answer : answers) {
			assertThat(answer.getId(), is(0));
			assertNull(answer.getAnswer());
		}
	}
	
	@Test
	@Order(3)
	@DisplayName("search_questions_idメソッドに、登録されているquestionIdを引数として渡した場合、答えを取得できること")
	public void getAnswers() throws Exception {
		QuestionsDao queDao = new QuestionsDao();
		//登録されている問題を全て取得
		ArrayList<QuestionsBean> queList = queDao.findAll();
			
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		//登録されているquestions_idを渡して、それと一致する答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.search_questions_id(queList.get(queList.size() - 1).getId());
		
		//答えが取得できることを確認
		assertThat(ansList.get(ansList.size() - 3).getAnswer(), is("a"));
		assertThat(ansList.get(ansList.size() - 2).getAnswer(), is("b"));
		assertThat(ansList.get(ansList.size() - 1).getAnswer(), is("c"));
	}
	
	@Test
	@Order(4)
	@DisplayName("delete_answersメソッドで、登録されていないquestionIdを引数として渡した場合、答えが削除できないこと")
	public void notDeleteAnswers() throws Exception {
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		//登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
		//登録されていないquestions_idを渡して、それと一致する答えを削除しようとする
		ansDao.delete_answers(10);
		//削除後に再度、登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
		
		//削除前と後で、データが減っていないことを確認
		assertThat(ansList.size() - ansList2.size(), is(0));
	}
	
	@Test
	@Order(5)
	@DisplayName("delete_answersメソッドで、登録されているquestionIdを引数として渡した場合、答えが削除できること")
	public void deleteAnswers() throws Exception {
		QuestionsDao queDao = new QuestionsDao();
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = queDao.findAll();
		
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		//登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
		//登録されているqustions_idを渡して、それと一致する答えを削除
		ansDao.delete_answers(queList.get(queList.size() - 1).getId());
		//削除後に再度、登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
		
		//削除前と後で、データが減っていることを確認
		assertThat(ansList.size() - ansList2.size(), is(3));
	}
	
	@Test
	@Order(6)
	@DisplayName("register_answerメソッドで、答えが1件登録できること")
	public void registerAnswer() throws Exception {
		QuestionsDao queDao = new QuestionsDao();
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = queDao.findAll();
		
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		//登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
		String answer = "A";
		//登録されているquestions_idを渡して、答えを登録
		ansDao.register_answer(queList.get(queList.size() - 1).getId(), answer);
		//登録後再度、登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
		
		//登録前と後で、答えのデータが1件増えていることを確認
		assertThat(ansList2.size() - ansList.size(), is(1));
		//最新の答えのデータが、先程登録したデータか確認
		assertThat(ansList2.get(ansList2.size() -1).getAnswer(), is("A"));
	}
	
	@Test
	@Order(8)
	@DisplayName("update_answerメソッドで、登録されていないanswerIdを引数として渡した場合、答えが更新されないこと")
	public void notUpdateAnswer() throws Exception {
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		String answer = "C";
		//登録されていないidを渡して、それと一致する答えを更新しようとする
		ansDao.update_answer(10, answer);
		//登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
		
		//取得した答えのデータに、更新した内容が含まれていないことを確認
		assertThat(ansList, hasItems(not("C")));
	}
	
	@Test
	@Order(7)
	@DisplayName("update_answerメソッドで、登録されているanswerIdを引数として渡した場合、答えが1件更新されること")
	public void updateAnswer() throws Exception {	
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		//登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
		String answer = "B";
		//登録されているidを渡して、それと一致する答えを更新
		ansDao.update_answer(ansList.get(ansList.size() - 1).getId(), answer);
		//更新後再度、登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
		
		//答えが更新されていることを確認
		assertThat(ansList2.get(ansList2.size() -1).getAnswer(), is("B"));
	}
	
	@Test
	@Order(9)
	@DisplayName("delete_answerメソッドで、登録されていないanswerIdを引数として渡した場合、答えが削除できないこと")
	public void notDeleteAnswer() throws Exception {
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		//登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
		//登録されていないidを渡して、それと一致する答えを削除しようとする
		ansDao.delete_answer(10);
		//更新後再度、登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
		
		//削除前と後で、データが減っていないことを確認
		assertThat(ansList.size() - ansList2.size(), is(0));
	}
	
	@Test
	@Order(10)
	@DisplayName("delete_answerメソッドで、登録されているanswerIdを引数として渡した場合、答えが1件削除できること")
	public void deleteAnswer() throws Exception {
		CorrectAnswersDao ansDao = new CorrectAnswersDao();
		//登録されている全ての答えを取得
		ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
		//登録されているidを渡して、それと一致する答えを削除する
		ansDao.delete_answer(ansList.get(ansList.size() - 1).getId());
		//更新後再度、登録されているすべての答えを取得
		ArrayList<CorrectAnswersBean> ansList2 = ansDao.findAll();
		
		//削除前と後で、データが減っていることを確認
		assertThat(ansList.size() - ansList2.size(), is(1));
	}
}
