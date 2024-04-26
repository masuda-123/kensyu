

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kensyu.HistoriesBean;
import kensyu.HistoriesDao;
import kensyu.UsersBean;
import kensyu.UsersDao;

/**
 * Servlet implementation class History
 */
public class History extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin(request, response)) {
			return ; //trueだった場合return
		}
		
		try {
			//セッションの取得
			HttpSession session = request.getSession(false);
			//セッションからuserIdを取得し、変数に格納
			int userId = (int)session.getAttribute("userId");
			
			HistoriesDao hisDao = new HistoriesDao();
			//search_userIdメソッドを呼び出し、ユーザーの履歴を取得
			ArrayList<HistoriesBean> hisList = hisDao.search_userId(userId);
				
			//履歴が採点日時の昇順に表示されるように、並び替えの条件を定義
			Comparator<HistoriesBean> compare = Comparator.comparing(HistoriesBean::getCreatedAt);
			//指定した条件で並び替える
			hisList.sort(compare);
			
			UsersDao dao = new UsersDao();
			//search_idメソッドを呼び出し、userIdに一致するレコードを取得
			UsersBean user = dao.search_id(userId);
			
			//次の遷移先の表示に必要な値をリクエストスコープにセット
			request.setAttribute("hisList", hisList);
			request.setAttribute("userName", user.getName());
			
			//History画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/History.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			//スタックトレースを出力
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
