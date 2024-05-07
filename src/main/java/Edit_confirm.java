

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.Validation;

/**
 * Servlet implementation class Edit_confirm
 */
public class Edit_confirm extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit_confirm() {
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
		String questionId = request.getParameter("questionId");
		String[] answers = request.getParameterValues("answer");
		String[] answersId = request.getParameterValues("answerId");
		
		//Validationクラスで問題文や答えの文字数などをチェックし、エラーメッセージを取得
		Validation val = new Validation();
		String errorMessage = val.validate(question, answers);
		
		//次の遷移先の表示に必要な値をリクエストスコープにセット
		request.setAttribute("question", question);
		request.setAttribute("answers", answers);
		request.setAttribute("questionId", questionId);
		request.setAttribute("answersId", answersId);
		request.setAttribute("errorMessage", errorMessage);
		
		//Edit_confirm画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/Edit_confirm.jsp");
		rd.forward(request, response);
	}

}
