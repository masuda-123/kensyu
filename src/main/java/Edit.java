

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.CorrectAnswersBean;
import kensyu.CorrectAnswersDao;
import kensyu.QuestionsBean;
import kensyu.QuestionsDao;

/**
 * Servlet implementation class Edit
 */
public class Edit extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin(request, response)) {
			return ; //trueだった場合return
		}
		try {
			//URLパラメータからquestionIdを取得
			int questionId = Integer.parseInt(request.getParameter("id"));
			//QuestionsDaoオブジェクトを作成
			QuestionsDao queDao = new QuestionsDao();
			//search_idメソッドを呼び出して、questionIdと一致するレコードを取得
			QuestionsBean question = queDao.search_id(questionId);
			//リクエストスコープへquestionを格納
			request.setAttribute("question", question.getQuestion());
			//リクエストスコープへquestionIdを格納
			request.setAttribute("questionId", questionId);
			//CorrectAnswersDaoオブジェクトを作成
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			//search_question_idメソッドを呼び出して、questionIdと一致するレコードを取得
			ArrayList<CorrectAnswersBean> answers = ansDao.search_questions_id(questionId);
			//リクエストスコープへanswersを格納
			request.setAttribute("answers", answers);
			//画面の遷移先としてEdit画面を定義
			RequestDispatcher rd = request.getRequestDispatcher("/Edit.jsp");
			//foward(...)で定義された転送先に処理が移る
			rd.forward(request, response);
			return;
		//try文の中で例外が発生した場合、catch句に処理が移る
		} catch (Exception e) {
			//スタックトレースを出力する
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
