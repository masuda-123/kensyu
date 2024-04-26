

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
			return ; //trueだった場合return
		}
		try {
			QuestionsDao queDao = new QuestionsDao();
			//findAllメソッドを呼び出し、登録されている問題を取得
			ArrayList<QuestionsBean> queList = queDao.findAll();
			
			CorrectAnswersDao ansDao = new CorrectAnswersDao();
			//findAllメソッドを呼び出し、登録されている答えを取得
			ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
			
			//次の遷移先の表示に必要な値をリクエストスコープにセット
			request.setAttribute("queList", queList);
			request.setAttribute("ansList", ansList);
				
			if(queList.isEmpty()) { //登録されている問題がなかった場合
				//Top画面に遷移
				RequestDispatcher rd = request.getRequestDispatcher("/Top.jsp");
				rd.forward(request, response);
				return;
			} else { //登録されている問題があった場合
				//List画面に遷移
				RequestDispatcher rd = request.getRequestDispatcher("/List.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			//スタックトレースを出力する
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
