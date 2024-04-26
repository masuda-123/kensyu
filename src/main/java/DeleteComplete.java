

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.CorrectAnswersDao;
import kensyu.QuestionsDao;

/**
 * Servlet implementation class DeleteComplete
 */
public class DeleteComplete extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin(request, response)) {
			return ; //trueだった場合return
		}
		try {
			QuestionsDao queDao = new QuestionsDao();
			CorrectAnswersDao ansDao = new CorrectAnswersDao(); 
			
			//フォームから渡された値を、変数に格納
			int questionId = Integer.parseInt(request.getParameter("questionId"));
			
			//delete_questionメソッドを呼び出して、問題を削除
			queDao.delete_question(questionId);
			//delete_answersメソッドを呼び出して、答えを削除
			ansDao.delete_answers(questionId);
			
			//List画面にリダイレクトする
			response.sendRedirect("./List");
			
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
		}
	}

}
