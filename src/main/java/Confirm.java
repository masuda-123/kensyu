

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Confirm
 */
public class Confirm extends HttpServlet {
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
		RequestDispatcher rd = request.getRequestDispatcher("/Confirm.jsp");
		//foward(...)で定義された転送先に処理が移る
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
	    HttpSession session = request.getSession(false);
	    // 既にセッションが存在する場合は一度破棄する
	    if (session != null) {
	      session.invalidate();
	    }
	    
	    // セッションを新規で作成する
	    session = request.getSession(true);
	    
		String question = request.getParameter("question");
		String[] answers = request.getParameterValues("answer");
		
		request.setAttribute("reg_question", question);
		request.setAttribute("reg_answers", answers);
		
		session.setAttribute("reg_question", question);
		session.setAttribute("reg_answers", answers);
		
		//questionが空だった場合
		if(question.isEmpty()) {
			request.setAttribute("error_empty_question", "※問題文を入力してください。");
		//questionの文字数が500文字を超えていた場合
		} else if(question.length() > 500) {
			request.setAttribute("error_length_question", "※問題の文字数が制限（500文字）を超えています。");
		}
		
		//answerの文字数が200文字を超えていた場合
		for(int i = 0; i < answers.length; i++) {
			if(answers[i].length() > 200) {
				request.setAttribute("error_length_answer", "※答えの文字数が制限（200文字）を超えています。");
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Confirm.jsp");
		rd.forward(request, response);
		return;
	}

}