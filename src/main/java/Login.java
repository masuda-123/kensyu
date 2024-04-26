

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
		
		//Login画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
		
		int userId = 0;
		if(request.getParameter("userId") != "") { //フォームから渡された値があった場合
			//フォームから渡されたuserIdを、変数に格納
			userId = Integer.parseInt(request.getParameter("userId"));
		}
		//フォームから渡されたpasswordを、変数に格納
		String password = request.getParameter("password");
		
		try {
			UsersDao dao = new UsersDao();
			//search_idメソッドを呼び出して、userIdと一致するレコードを取得する。
			UsersBean user = dao.search_id(userId);
			
			//フォームから渡されたIDと一致するレコードがあり、そのレコードのパスワードが、入力されたパスワードと一致する場合
			if(user.getId() != 0 && user.getPassword().equals(password)) {
				//セッションの取得
				HttpSession session = request.getSession(false);
				if (session != null) { //既にセッションが存在する場合
					//セッションを破棄
					session.invalidate();
				}
				//セッションを新規作成
				session = request.getSession(true);
				//セッションに、userIdを格納
				session.setAttribute("userId", userId);
				
				//Top画面に遷移
				RequestDispatcher rd = request.getRequestDispatcher("/Top.jsp");
				rd.forward(request, response);
				return;
				
			} else { //IDに一致するレコードがなかった場合もしくは、パスワードが一致しなかった場合
				//Login画面に遷移
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
		}
	}

}