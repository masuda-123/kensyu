

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.CorrectAnswersBean;
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
			int[] answersId =  Stream.of(request.getParameterValues("answerId")).mapToInt(Integer::parseInt).toArray();
			
			//questionの更新
			queDao.update_question(question, questionId);
			
			//既存の答えのデータを取得
			ArrayList<CorrectAnswersBean> ansRecord = ansDao.search_questions_id(questionId);
			
			int i = 0;
			for(; i < answersId.length; i++) {
				System.out.println("いまここ");
				ansDao.update_answer(answersId[i], answers[i]);
			}
			for(; i < answers.length; i++) {
				System.out.println("次ここ");
				ansDao.register_answer(questionId, answers[i]);
			}
			if(ansRecord.size() > answersId.length) {
				int[] regAnswersId = new int[ansRecord.size()];
				for(int j = 0; j < ansRecord.size(); j++) {
					regAnswersId[j] = ansRecord.get(j).getId();
				}
				int deleteId = Arrays.mismatch(answersId, regAnswersId);
				if( deleteId != -1) {
					System.out.println("最後ここ");
					System.out.println(deleteId);
					ansDao.delete_answer(regAnswersId[deleteId]);
				}
			}
			
			response.sendRedirect("./List");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
