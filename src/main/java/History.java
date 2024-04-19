

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
public class History extends HttpServlet {
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
		
		HttpSession session = request.getSession(false);
		//セッションが存在しない場合
		if (session == null || (session.getAttribute("userId")) == null){
			//ログインページに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/Login.jsp");
			dispatch.forward(request, response);
			return;
		} else {
			try {
				//sessionからuser_idを取得
				int userId = (int)session.getAttribute("userId");
				
				//取得したuser_idに一致する履歴を取得
				HistoriesDao hisDao = new HistoriesDao();
				ArrayList<HistoriesBean> hisList = hisDao.search_userId(userId);
				
				//採点時間の昇順に並び替える
				Comparator<HistoriesBean> compare = Comparator.comparing(HistoriesBean::getCreatedAt);
				hisList.sort(compare);
				
				//user_idからuser情報を取得
				UsersDao dao = new UsersDao();
				UsersBean user = dao.search_id(userId);
				
				request.setAttribute("hisList", hisList);
				request.setAttribute("userName", user.getName());
				
				RequestDispatcher rd = request.getRequestDispatcher("/History.jsp");
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
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
