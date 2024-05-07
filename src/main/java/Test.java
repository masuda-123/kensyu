

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kensyu.QuestionsBean;
import kensyu.QuestionsDao;

/**
 * Servlet implementation class Test
 */
public class Test extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
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
			//次の遷移先の表示に必要な値をリクエストスコープにセット
			request.setAttribute("queList", queList);
			//Test画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/Test.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			//スタックトレースを出力
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
