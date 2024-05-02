

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kensyu.CorrectAnswersBean;
import kensyu.CorrectAnswersDao;
import kensyu.HistoriesDao;
import kensyu.UsersBean;
import kensyu.UsersDao;

/**
 * Servlet implementation class Result
 */
public class Result extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
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
		//フォームから渡された値を、それぞれ変数に格納
		int[] questionsId = Stream.of(request.getParameterValues("questions_id")).mapToInt(Integer::parseInt).toArray();
		String[] answers = request.getParameterValues("answer");
		
		try {
			int correctQueCnt = 0;
			
			//questionsIdの要素数分だけ処理を繰り返す
			for(int i = 0; i < questionsId.length; i++) {
				CorrectAnswersDao ansDao = new CorrectAnswersDao();
				//search_answerメソッドを呼び出して、answerと一致するレコードを取得
				ArrayList<CorrectAnswersBean> ansList = ansDao.search_answer(answers[i]);
				
				//ansListの要素数分だけ繰り返す
				for(CorrectAnswersBean ans : ansList ) {
					//入力された答えと一致するレコードがあり、答えに紐づく問題idが一致する場合
					if(ans.getId() != 0 && ans.getQuestionsId() == questionsId[i]) {
						//正解の問題数をカウントアップ
						correctQueCnt ++;
						//繰り返し処理を抜ける
						break;
					}
				}
			}
			//点数を計算
			int point = Math.round(100 * correctQueCnt / questionsId.length);
			
			//セッションの取得
			HttpSession session = request.getSession(false);
			//セッションからuserIdを取得し、変数に格納
			int userId = (int)session.getAttribute("userId");
			
			HistoriesDao hisDao = new HistoriesDao();
			//register_historyメソッドを呼び出し、履歴を登録
			hisDao.register_history(userId, point);
			
			UsersDao dao = new UsersDao();
			//sarch_idメソッドを呼び出し、userIdと一致するレコードを取得
			UsersBean user = dao.search_id(userId);
			
			//次の遷移先の表示に必要な値をリクエストスコープにセット
			request.setAttribute("correctQueCnt", correctQueCnt);
			request.setAttribute("queCnt", questionsId.length);
			request.setAttribute("point", point);
			request.setAttribute("userName", user.getName());
			request.setAttribute("date", hisDao.getStringCurrentTimestamp());
			
			//Result画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/Result.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
		}
	}
}
