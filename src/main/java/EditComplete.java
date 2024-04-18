

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.CorrectAnswersDao;
import kensyu.QuestionsDao;

/**
 * Servlet implementation class EditComplete
 */
public class EditComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
			QuestionsDao queDao = new QuestionsDao();
			CorrectAnswersDao ansDao = new CorrectAnswersDao(); 
			
			String question = request.getParameter("question");
			String[] answers = request.getParameterValues("answer");
			int questionId = Integer.parseInt(request.getParameter("questionId"));
			
			//questionの更新
			queDao.update_question(question, questionId);
			//既存のanswersを削除
			ansDao.delete_answers(questionId);
			//answersを登録
			ansDao.register_answers(questionId, answers);
			
			response.sendRedirect("./List");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
