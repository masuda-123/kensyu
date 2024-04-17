

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
public class Delete_confirm extends HttpServlet {
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
		
		//URLパラメータからquestionIdを取得
		int questionId = Integer.parseInt(request.getParameter("id"));
		try {
			QuestionsDao queDao = new QuestionsDao();
			//questionsテーブルからquestionIdが一致するレコードを取得
			QuestionsBean question = queDao.search_id(questionId);
			request.setAttribute("question", question.getQuestion());
			
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			//correct_answersテーブルからquestionIdが一致するレコードを取得
			ArrayList<CorrectAnswersBean> ansList = ansDao.search_questions_id(questionId);
			ArrayList<String> answers = new ArrayList<String>();
			for(CorrectAnswersBean ans : ansList) {
				 answers.add(ans.getAnswer());
			}
			request.setAttribute("answers", answers);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Delete_confirm.jsp");
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
		//doGet(request, response);
	}
}
