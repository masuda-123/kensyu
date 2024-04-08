package kensyu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CorrectAnswersDao extends ConnectionDao {
	/**
	 * コンストラクタ（戻り値のない）
	 */
	// 初期値化
	public CorrectAnswersDao() throws Exception {
		setConnection();
	}

	/**
	 * users テーブルを全件取得
	 */
	public ArrayList<CorrectAnswersBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// correct_answersからidとquestions_id、answerを取得
			String sql = "SELECT id, questions_id, answer FROM correct_answers";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questions_id, answer);
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
	
	/**
	 * 指定IDのレコードを取得する
	 */
	
	// CorrectAnswersBean型のidを引数として受け取る
	public CorrectAnswersBean search_correct_answer_id(int id) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// CorrectAnswers tableのidとquestions_id、answerを取得
			String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE id = ? ";
		
			
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			CorrectAnswersBean list = new CorrectAnswersBean();
			while (rs.next()) {
				// 一旦変数で受ける
				int correct_answer_id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				// ListはCorrectAnswers型
				list = new CorrectAnswersBean(correct_answer_id, questions_id, answer);
			
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
}
