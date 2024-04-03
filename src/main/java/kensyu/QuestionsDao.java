package kensyu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDao extends ConnectionDao {

	/**
	 * コンストラクタ（戻り値のない）
	 */
	// 初期値化
	public QuestionsDao() throws Exception {
		setConnection();
	}

	/**
	 * users テーブルを全件取得
	 */
	public List<QuestionBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// questionsからidとquestionを取得
			String sql = "SELECT id, question FROM questions";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<QuestionBean> list = new ArrayList<QuestionBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				QuestionBean bean = new QuestionBean(id, question);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			// リソースの開放
			try {
				if (rs != null) {
						rs.close();
				}

				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("リソースの開放に失敗しました");

			}
		}
	}
	// UserBean型　idを引数として受け取る
	public QuestionBean search_questionid(int id) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// Questions tableのidとquestionを取得
			String sql = "SELECT id, question FROM users WHERE id = ? ";
			
		
			
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			QuestionBean list = new QuestionBean();
			while (rs.next()) {
				// 一旦変数で受ける
				int questionid = rs.getInt("id");
				String question = rs.getString("question");
				// ListはUserBean型
				list = new QuestionBean(questionid, question);
			
			}
			return list;
		} catch (Exception e) { 
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}

				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("リソースの開放に失敗しました");

			}
		}
	}
	/**
	 * 指定IDのレコードを取得する
	 */
	
}