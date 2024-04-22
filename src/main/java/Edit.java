

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
public class Edit extends HttpServlet {
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
		
		try {
			//URLパラメータからquestionIdを取得
			int questionId = Integer.parseInt(request.getParameter("id"));
			
			QuestionsDao queDao = new QuestionsDao();
			//questionsテーブルからquestionIdが一致するレコードを取得
			QuestionsBean question = queDao.search_id(questionId);
			request.setAttribute("question", question.getQuestion());
			request.setAttribute("questionId", questionId);
			
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			//correct_answersテーブルからquestionIdが一致するレコードを取得
			ArrayList<CorrectAnswersBean> answers = ansDao.search_questions_id(questionId);
			request.setAttribute("answers", answers);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Edit.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
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
