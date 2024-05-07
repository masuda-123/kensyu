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
	public void registerQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		
		//問題を３つ登録する
		String question = "testtest";
		dao.register_question(question);
		String question2 = "testtest2";
		dao.register_question(question2);
		String question3 = "testtest3";
		dao.register_question(question3);
		
		//問題登録後に再度、登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList2 = dao.findAll();
		
		//登録前と登録後で、問題が3つ増えたことを確認
		assertThat(queList2.size() - queList.size(), is(3));
		
		//最新の問題が、先程登録したデータかを確認
		assertThat(queList2.get(queList2.size()-3).getQuestion(), is("testtest"));
		assertThat(queList2.get(queList2.size()-2).getQuestion(), is("testtest2"));
		assertThat(queList2.get(queList2.size()-1).getQuestion(), is("testtest3"));
	}
	
	@Test
	@Order(2)
	@DisplayName("search_idメソッドに、登録されていないquestionIdを引数として渡した場合、問題が取得できないこと")
	public void notGetQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されていないidに一致する問題を取得しようとする
		QuestionsBean question = dao.search_id(4);
		
		//問題データが取得できていないことを確認
		assertThat(question.getId(), is(0));
		assertNull(question.getQuestion());
	}
	
	@Test
	@Order(3)
	@DisplayName("search_idメソッドに、登録されているquestionIdを引数として渡した場合、問題が取得できること")
	public void getQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		//最新の問題idを取得
		int questionId = queList.get(queList.size() - 1).getId();
		//idに一致する問題を取得
		QuestionsBean question = dao.search_id(questionId);
		
		//問題が取得できていることを確認
		assertThat(question.getQuestion(), is("testtest3"));
	}
	
	@Test
	@Order(4)
	@DisplayName("register_questionメソッドで、登録されていないquestionIdを引数として渡した場合、問題が更新できないこと")
	public void notUpdateQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		String question = "テストテスト";
		//登録されていないidに一致する問題を更新しようとする
		dao.update_question(question, 10);
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		
		//取得した問題に、更新した内容が含まれていないことを確認
		assertThat(queList, hasItems(not("テストテスト")));
	}
	
	@Test
	@Order(5)
	@DisplayName("register_questionメソッドで、登録されているquestionIdを引数として渡した場合、問題が更新できること")
	public void updateQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		String question = "テストテスト";
		//登録されている問題idと一致する問題を更新する
		dao.update_question(question, queList.get(queList.size() - 1).getId());
		//更新後に再度、登録されている全ての問題データを取得;
		ArrayList<QuestionsBean> queList2 = dao.findAll();
		
		//問題が更新されていることを確認
		assertThat(queList2.get(queList.size() - 1).getQuestion(), is("テストテスト"));
	}
	
	@Test
	@Order(6)
	@DisplayName("delete_questionメソッドで、登録されていないquestionIdを引数として渡した場合、問題の削除ができないこと")
	public void notDeleteQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		//登録されていないidと一致する問題を削除しようとする
		dao.delete_question(10);
		//削除後に再度、登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList2 = dao.findAll();
		
		//削除前と削除後で問題の数が変わっていないことを確認
		assertThat(queList.size() - queList2.size(), is(not(1)));
	}
	
	@Test
	@Order(7)
	@DisplayName("delete_questionメソッドで、登録されているquestionIdを引数として渡した場合、問題の削除ができること")
	public void deleteQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		//3つの問題idを渡して、その問題を削除する
		int questionId1 = queList.get(queList.size() - 1).getId();
		dao.delete_question(questionId1);
		int questionId2 = queList.get(queList.size() - 2).getId();
		dao.delete_question(questionId2);
		int questionId3 = queList.get(queList.size() - 3).getId();
		dao.delete_question(questionId3);
		//削除後、改めて登録されている全ての問題を取得
		ArrayList<QuestionsBean> queList2 = dao.findAll();
		
		//削除前と削除後で問題が3つ減ったことを確認
		assertThat(queList.size() - queList2.size(), is(3));
	}
}
