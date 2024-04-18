

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Edit_confirm
 */
public class Edit_confirm extends HttpServlet {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		String question = request.getParameter("question");
		String questionId = request.getParameter("questionId");
		String[] answers = request.getParameterValues("answer");
		String[] answersId = request.getParameterValues("answerId");
		
		request.setAttribute("question", question);
		request.setAttribute("answers", answers);
		request.setAttribute("questionId", questionId);
		request.setAttribute("answersId", answersId);
		
		String errorMessage = "";
		boolean isNewLine = false;
		
		if(question.isEmpty()) { //questionが空だった場合
			errorMessage += "※問題を入力してください。";
			isNewLine = true;
		} else if(question.length() > 500) { //questionの文字数が500文字を超えていた場合
			errorMessage += "※問題の文字数が制限（500文字）を超えています。";
			isNewLine = true;
		}
		
		if (isNewLine) { // エラーメッセージが存在したら改行タグを追加
			errorMessage += "<br>";
		}
		
		for(int i = 0; i < answers.length; i++) {
			isNewLine = false;
			if(answers[i].isEmpty()) { 	//answerが空だった場合
				errorMessage += "※答え" + (i+1) + "が未入力です。";
				isNewLine = true;
			}else if(answers[i].length() > 200) { //answerの文字数が200文字を超えていた場合
				errorMessage += "※答え" + (i+1) + "の文字数が制限（200文字）を超えています。";
				isNewLine = true;
			}
			if (isNewLine) { // エラーメッセージが存在したら改行を追加
				errorMessage += "<br>";
			}
		}
		
		request.setAttribute("errorMessage", errorMessage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Edit_confirm.jsp");
		rd.forward(request, response);
		return;
	}

}
