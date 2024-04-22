

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
			ArrayList<CorrectAnswersBean> ansList = ansDao.search_questions_id(questionId);
			
			for(int i = 0; i < answers.length; i++) {
				if( i < answersId.length) { //更新された答えがあった場合
					ansDao.update_answer(answersId[i], answers[i]); //answerの更新
				} else { //新たに追加された答えがあった場合
					ansDao.register_answer(questionId, answers[i]); //answerの登録
				}
			}
			if(ansList.size() > answersId.length) { //削除された答えがあった場合
				for(CorrectAnswersBean ans : ansList) {
					if(!(Arrays.stream(answersId).anyMatch(x -> x == ans.getId()))){
						ansDao.delete_answer(ans.getId()); //answerの削除
					}
				}
			}
			
			response.sendRedirect("./List");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
