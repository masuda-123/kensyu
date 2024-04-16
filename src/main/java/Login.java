

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kensyu.UsersBean;
import kensyu.UsersDao;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
		//foward(...)で定義された転送先に処理が移る
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
		int userId = Integer.parseInt(request.getParameter("userId"));
		String password = request.getParameter("password");
		
		try {
			UsersDao dao = new UsersDao();
			UsersBean user = dao.search_id(userId);
			
			//IDとパスワードが一致した場合
			if(user.getId() != 0 && user.getPassword().equals(password)) {
			    HttpSession session = request.getSession(false);
			    // 既にセッションが存在する場合は一度破棄する
			    if (session != null) {
			      session.invalidate();
			    }
			    // セッションを新規で作成して、user_idを格納
			    session = request.getSession(true);
				session.setAttribute("userId", user.getId());
				
				RequestDispatcher rd = request.getRequestDispatcher("/Top.jsp");
				rd.forward(request, response);
				return;
				
			//IDとパスワードが一致しなかった場合
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
