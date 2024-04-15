

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

/**
 * Servlet implementation class Result
 */
public class Result extends HttpServlet {
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
		RequestDispatcher rd = request.getRequestDispatcher("/Result.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		int[] questions_id = Stream.of(request.getParameterValues("questions_id")).mapToInt(Integer::parseInt).toArray();
		String[] answers = request.getParameterValues("answer");
		
		//正解の問題数をカウント
		int correctQueCnt = 0;
		
		for(int i = 0; i < questions_id.length; i++) {
			try {
				CorrectAnswersDao a_dao = new CorrectAnswersDao();
				//入力された答えと一致するレコードを全て取得
				ArrayList<CorrectAnswersBean> a_list = a_dao.search_answer(answers[i]);
				for(CorrectAnswersBean ans : a_list ) {
					//入力された答えと一致するレコードがあり、答えに紐づく問題が一致する場合
					if(ans.getId() != 0 && ans.getQuestionsId() == questions_id[i]) {
						correctQueCnt ++;
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//点数を計算
		int point = Math.round(100 * correctQueCnt / questions_id.length);
		
		HttpSession session = request.getSession(false);
		UsersBean user = (UsersBean)session.getAttribute("user");
		
		request.setAttribute("correctQueCnt", correctQueCnt);
		request.setAttribute("queCnt", questions_id.length);
		request.setAttribute("point", point);
		request.setAttribute("userName", user.getName());
		
		//履歴を登録
		try {
			HistoriesDao h_dao = new HistoriesDao();
			h_dao.register_history(user.getId(), point);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Result.jsp");
		rd.forward(request, response);
		
		
		
	}

}
