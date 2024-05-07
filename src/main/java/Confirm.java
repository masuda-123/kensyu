

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.Validation;

/**
 * Servlet implementation class Confirm
 */
public class Confirm extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirm() {
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
		
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin(request, response)) {
			return ; //trueだった場合return
		}
		//フォームから渡された値を、それぞれ変数に格納
		String question = request.getParameter("question");
		String[] answers = request.getParameterValues("answer");
		request.setAttribute("question", question);
		request.setAttribute("answers", answers);
		
		//Validationクラスで問題文や答えの文字数などをチェックし、エラーメッセージを取得
		Validation val = new Validation();
		String errorMessage = val.validate(question, answers);
		
		//リクエストスコープへerrorMessageを格納
		request.setAttribute("errorMessage", errorMessage);
		
		//Confirm画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/Confirm.jsp");
		rd.forward(request, response);
	}
}
