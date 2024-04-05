

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String password = request.getParameter("password");
		
		try {
			UsersDao dao = new UsersDao();
			UsersBean bean = dao.search_userid(user_id);
			
			if(bean.getId() == 0 || !(bean.getPassword().equals(password))) {
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				//foward(...)で定義された転送先に処理が移る
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/Top.jsp");
				//foward(...)で定義された転送先に処理が移る
				rd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
