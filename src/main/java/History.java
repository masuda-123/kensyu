

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kensyu.HistoriesBean;
import kensyu.HistoriesDao;
import kensyu.UsersBean;

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
	
		try {
			//sessionからユーザー情報を取得
			HttpSession session = request.getSession(false);
			UsersBean user = (UsersBean)session.getAttribute("user");
			
			//ユーザー情報に一致する履歴を取得
			HistoriesDao hisDao = new HistoriesDao();
			ArrayList<HistoriesBean> hisList = hisDao.search_userId(user.getId());
			request.setAttribute("hisList", hisList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/History.jsp");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
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
