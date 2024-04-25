

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
			return ;
		}
		//フォームから渡された値を、変数questionに格納
		String question = request.getParameter("question");
		//フォームから渡された値を、配列answersに格納
		String[] answers = request.getParameterValues("answer");
		//リクエストスコープへquestionを格納
		request.setAttribute("question", question);
		//リクエストスコープへanswersを格納
		request.setAttribute("answers", answers);
		//エラーメッセージを格納する変数を宣言
		String errorMessage = "";
		//エラーメッセージを改行させるかどうか判断する際に利用する変数を宣言
		boolean isNewLine = false;
		
		if(question.isEmpty()) {  //問題文が空だった場合
			//エラーメッセージに文字列を追加
			errorMessage += "※問題を入力してください。";
			//改行するようtrueを格納
			isNewLine = true;
		} else if(question.length() > 500) { //問題文の文字数が500文字を超えていた場合
			//エラーメッセージに文字列を追加
			errorMessage += "※問題の文字数が制限（500文字）を超えています。";
			//改行するようtrueを格納
			isNewLine = true;
		}
		
		if (isNewLine) { // trueだった場合
			//エラーメッセージにbrタグを追加し、改行させる
			errorMessage += "<br>";
		}
		
		//答えの数だけ処理を繰り返す
		for(int i = 0; i < answers.length; i++) {
			//初期値に戻す
			isNewLine = false;
			if(answers[i].isEmpty()) { 	//答えが空だった場合
				//エラーメッセージに文字列を追加
				errorMessage += "※答え" + (i+1) + "が未入力です。";
				//改行するようtrueを格納
				isNewLine = true;
			}else if(answers[i].length() > 200) { //answerの文字数が200文字を超えていた場合
				//エラーメッセージに文字列を追加
				errorMessage += "※答え" + (i+1) + "の文字数が制限（200文字）を超えています。";
				//改行するようtrueを格納
				isNewLine = true;
			}
			if (isNewLine) { // エラーメッセージが存在したら改行を追加
				//エラーメッセージにbrタグを追加し、改行させる
				errorMessage += "<br>";
			}
		}
		//リクエストスコープへerrorMessageを格納
		request.setAttribute("errorMessage", errorMessage);
		//画面の遷移先としてConfirm画面を定義
		RequestDispatcher rd = request.getRequestDispatcher("/Confirm.jsp");
		//foward(...)で定義された転送先に処理が移る
		rd.forward(request, response);
	}
}
