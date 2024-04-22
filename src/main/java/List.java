

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.CorrectAnswersBean;
import kensyu.CorrectAnswersDao;
import kensyu.QuestionsBean;
import kensyu.QuestionsDao;

/**
 * Servlet implementation class List
 */
public class List extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin(request, response)) {
			return ; //trueだった場合returnさせる
		}
		try {
			//QuestionsDaoオブジェクトを作成
			QuestionsDao queDao = new QuestionsDao();
			//findAllメソッドを呼び出し、登録されている問題を取得
			ArrayList<QuestionsBean> queList = queDao.findAll();
			//リクエストスコープへqueListを格納
			request.setAttribute("queList", queList);
			//CorrectAnswersDaoオブジェクトを作成
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			//findAllメソッドを呼び出し、登録されている答えを取得
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			//リクエストスコープへansListを格納
			request.setAttribute("ansList", ansList);
				
			if(queList.isEmpty()) { //登録されている問題がなかった場合
				//画面の遷移先として、Top画面を定義
				RequestDispatcher rd = request.getRequestDispatcher("/Top.jsp");
				//foward(...)で定義された転送先に処理が移る
				rd.forward(request, response);
				return;
			} else { //登録されている問題があった場合
				//画面の遷移先として、List画面を定義
				RequestDispatcher rd = request.getRequestDispatcher("/List.jsp");
				//foward(...)で定義された転送先に処理が移る
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
