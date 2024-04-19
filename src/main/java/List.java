

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kensyu.CorrectAnswersBean;
import kensyu.CorrectAnswersDao;
import kensyu.QuestionsBean;
import kensyu.QuestionsDao;

/**
 * Servlet implementation class List
 */
public class List extends HttpServlet {
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

		HttpSession session = request.getSession(false);
		//セッションが存在しない場合
		if (session == null || (session.getAttribute("userId")) == null){
			//ログインページに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/Login.jsp");
			dispatch.forward(request, response);
			return;
		} else {
			try {
				QuestionsDao queDao = new QuestionsDao();
				ArrayList<QuestionsBean> queList = queDao.findAll();
				request.setAttribute("queList", queList);
				CorrectAnswersDao ansDao = new CorrectAnswersDao();
				ArrayList<CorrectAnswersBean> ansList = ansDao.findAll();
				request.setAttribute("ansList", ansList);
				
				if(queList.isEmpty()) {
					RequestDispatcher rd = request.getRequestDispatcher("/Top.jsp");
					rd.forward(request, response);
					return;
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/List.jsp");
					rd.forward(request, response);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		

	}

}
