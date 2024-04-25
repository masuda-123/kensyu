

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
public class EditComplete extends Base {
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
			//QuestionsDaoオブジェクトを作成
			QuestionsDao queDao = new QuestionsDao();
			//CorrectAnswersDaoオブジェクトを作成
			CorrectAnswersDao ansDao = new CorrectAnswersDao(); 
			//フォームから渡された値を、変数questionに格納
			String question = request.getParameter("question");
			//フォームから渡された値を、配列answersに格納
			String[] answers = request.getParameterValues("answer");
			//フォームから渡された値を、int型に変換して変数questionIdに格納
			int questionId = Integer.parseInt(request.getParameter("questionId"));
			//フォームから渡された値を、int型に変換して、配列answersIdに格納
			int[] answersId =  Stream.of(request.getParameterValues("answerId")).mapToInt(Integer::parseInt).toArray();
			
			//update_questionメソッドを呼び出して、問題を更新
			queDao.update_question(question, questionId);
			
			//search_questions_idメソッドを呼び出して、questionIdと一致するレコードを取得し、既存の答えのデータを取得
			ArrayList<CorrectAnswersBean> ansList = ansDao.search_questions_id(questionId);
			
			//フォームから渡された答えの数だけ処理を繰り返す
			for(int i = 0; i < answers.length; i++) {
				if( i < answersId.length) { //フォームから渡された答えの中に、idを持つものがあった場合（更新された答えがあった場合）
					ansDao.update_answer(answersId[i], answers[i]); //update_answerメソッドを呼び出して、答えを更新
				} else { //idを持たない答えがあった場合（新たに追加された答えがあった場合）
					ansDao.register_answer(questionId, answers[i]); //register_answerメソッドを呼び出して、答えを登録
				}
			}
			if(ansList.size() > answersId.length) { //既存の答えの数の方が、フォームから渡されたidの数より多かった場合（削除された答えがあった場合）
				for(CorrectAnswersBean ans : ansList) { //既存の答えの数だけ、処理を繰り返す
					if(!(Arrays.stream(answersId).anyMatch(x -> x == ans.getId()))){ //既存の答えにしかないidがあった場合
						ansDao.delete_answer(ans.getId()); //delete_answerメソッドを呼び出して、答えを削除
					}
				}
			}
			//./ListのURLにリダイレクトする
			response.sendRedirect("./List");
			return;
		//try文の中で例外が発生した場合、catch句に処理が移る
		} catch (Exception e) {
			//スタックトレースを出力する
			e.printStackTrace();
		}
	}

}
