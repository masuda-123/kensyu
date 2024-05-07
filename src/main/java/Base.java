

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Base
 */
public class Base extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Base() {
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
	}
	
	/**
	 * ログインチェック判定
	 */
	protected boolean isCheckLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションを取得
		HttpSession session = request.getSession(false);
		//セッションが空だった場合もしくは、セッションにuserIdが格納されていない場合
		if (session == null || (session.getAttribute("userId")) == null){
			//Login画面に遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/Login.jsp");
			dispatch.forward(request, response);
			//trueを返す
			return true;
		}
		//セッションにuserIdがある場合、falseを返す
		return false;
	}

}
