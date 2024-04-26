

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
 * Servlet implementation class Delete_confirm
 */
public class Delete_confirm extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_confirm() {
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
			
			QuestionsDao queDao = new QuestionsDao();
			//search_idメソッドを呼び出して、questionIdと一致するレコードを取得
			QuestionsBean question = queDao.search_id(questionId);

			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			//search_question_idメソッドを呼び出して、questionIdと一致するレコードを取得
			ArrayList<CorrectAnswersBean> ansList = ansDao.search_questions_id(questionId);
			
			//asListをArrayList型から配列に変換
			String[] answers = new String[ansList.size()];
			for(int i = 0; i < ansList.size(); i++) {
				answers[i] = ansList.get(i).getAnswer();
			}
			
			//次の遷移先の表示に必要な値をリクエストスコープにセット
			request.setAttribute("question", question.getQuestion());
			request.setAttribute("questionId", questionId);
			request.setAttribute("answers", answers);
			
			//画面の遷移先としてDelete_confirm画面を定義
			RequestDispatcher rd = request.getRequestDispatcher("/Delete_confirm.jsp");
			//foward(...)で定義された転送先に処理が移る
			rd.forward(request, response);
			
		} catch (Exception e) {
			//スタックトレースを出力
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
