

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.CorrectAnswersDao;
import kensyu.QuestionsDao;

/**
 * Servlet implementation class RegisterComplete
 */
public class RegisterComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterComplete() {
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
		
		try {
			QuestionsDao q_dao = new QuestionsDao();
			CorrectAnswersDao a_dao = new CorrectAnswersDao(); 
			
			String question = request.getParameter("question");
			String[] answers = request.getParameterValues("answer");
			
			//questionの登録とquestions_idを取得
			int questions_id = q_dao.register_question(question);
			//answersの登録
			a_dao.register_answers(questions_id, answers);
			
			response.sendRedirect("../List");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
