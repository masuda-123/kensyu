

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
		
		//画面の遷移先としてLogin画面を定義
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
		
		//フォームから渡された値をint型に変換して、変数userIdに格納
		int userId = Integer.parseInt(request.getParameter("userId"));
		//フォームから渡された値を、変数passwordに格納
		String password = request.getParameter("password");
		
		try {
			//UsersDaoオブジェクトを作成し、変数daoに格納
			UsersDao dao = new UsersDao();
			//search_idメソッドを呼び出して、userIdと一致するレコードを取得する。
			UsersBean user = dao.search_id(userId);
			
			//フォームから渡されたIDとパスワードの値が、取得したレコードと一致した場合
			if(user.getId() != 0 && user.getPassword().equals(password)) {
				//セッションの取得
				HttpSession session = request.getSession(false);
				//既にセッションが存在する場合
				if (session != null) {
					//セッションを破棄する
					session.invalidate();
				}
				//セッションを新規で作成
				session = request.getSession(true);
				//セッションに、userIdを格納
				session.setAttribute("userId", userId);
				
				//画面の遷移先としてTop画面を定義
				RequestDispatcher rd = request.getRequestDispatcher("/Top.jsp");
				//foward(...)で定義された転送先に処理が移る
				rd.forward(request, response);
				return;
				
			//IDとパスワードが一致しなかった場合
			} else {
				//画面の遷移先としてLogin画面を定義
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				//foward(...)で定義された転送先に処理が移る
				rd.forward(request, response);
				return;
			}
		//try文の中で例外が発生した場合、catch句に処理が移る
		} catch (Exception e) {
			//スタックトレースを出力する
			e.printStackTrace();
		}
	}

}
