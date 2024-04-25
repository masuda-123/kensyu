

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
			//セッションからuserIdを取得し、int型に変換し、変数userIdに格納
			int userId = (int)session.getAttribute("userId");
			
			//HisotiresDaoオブジェクトを作成
			HistoriesDao hisDao = new HistoriesDao();
			//search_userIdメソッドを呼び出し、ユーザーの履歴を取得
			ArrayList<HistoriesBean> hisList = hisDao.search_userId(userId);
				
			//履歴が採点日時の昇順に表示されるように、並び替えの条件を定義
			Comparator<HistoriesBean> compare = Comparator.comparing(HistoriesBean::getCreatedAt);
			//指定した条件で並び替える
			hisList.sort(compare);
				
			//UsersDaoオブジェクトを作成
			UsersDao dao = new UsersDao();
			//search_idメソッドを呼び出し、userIdに一致するレコードを取得
			UsersBean user = dao.search_id(userId);
			
			//リクエストスコープへhisListを格納
			request.setAttribute("hisList", hisList);
			//リクエストスコープへユーザー名を格納
			request.setAttribute("userName", user.getName());
			
			//画面の遷移先として、History画面を定義
			RequestDispatcher rd = request.getRequestDispatcher("/History.jsp");
			//foward(...)で定義された転送先に処理が移る
			rd.forward(request, response);
			return;
		//try文の中で例外が発生した場合、catch句に処理が移る
		} catch (Exception e) {
			//スタックトレースを出力する
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
