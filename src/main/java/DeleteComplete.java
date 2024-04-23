

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
		
		if (super.isCheckLogin(request, response)) {
			return ;
		}
		try {
			QuestionsDao queDao = new QuestionsDao();
			CorrectAnswersDao ansDao = new CorrectAnswersDao(); 
			//パラメータからquestionIdを取得
			int questionId = Integer.parseInt(request.getParameter("questionId"));
			
			//questionの削除
			queDao.delete_question(questionId);
			//answersの削除
			ansDao.delete_answers(questionId);
			
			response.sendRedirect("./List");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
