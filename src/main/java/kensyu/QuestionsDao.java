package kensyu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionsDao extends ConnectionDao {

	/**
	 * コンストラクタ（戻り値のない）
	 */
	// 初期値化
	public QuestionsDao() throws Exception {
		setConnection();
	}
	/**
	 * questions テーブルを全件取得
	 */
	public ArrayList<QuestionsBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		// questionsからidとquestionを取得
		String sql = "SELECT id, question FROM questions";
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			/** select文の結果をArrayListに格納 **/
			ArrayList<QuestionsBean> list = new ArrayList<QuestionsBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				QuestionsBean bean = new QuestionsBean(id, question);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	/**
	 * 問題を登録する
	*/
	public int register_question(String question) throws Exception {
		if (con == null) {
			setConnection();
		}
		// Questions table にデータを追加
		String insert_sql = "INSERT INTO questions (question, created_at) VALUES (?, CURRENT_TIMESTAMP());";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(insert_sql);) {
			insert_st.setString(1, question);
			/** SQL 実行 **/
			insert_st.executeUpdate();
			// Questions table のidの最大値を取得
			String select_sql = "SELECT MAX(id) from questions;";
			/** PreparedStatement オブジェクトの取得とSQLの実行**/
			try(PreparedStatement select_st = con.prepareStatement(select_sql);
					ResultSet rs = select_st.executeQuery()) {
				/** select文で取得したidを変数に格納 **/ 
				int questions_id = 0;
				while (rs.next()) {
					questions_id = rs.getInt("MAX(id)");
				}
				return questions_id;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました");
		}
		
	}
}