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
	 * correct_answers テーブルを全件取得
	 */
	public ArrayList<CorrectAnswersBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		// correct_answersからidとquestions_id、answerを取得
		String sql = "SELECT id, questions_id, answer FROM correct_answers";
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
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
		}
	}
	/**
	 * 入力されたanswerに一致するレコードを取得する
	 */
	public ArrayList<CorrectAnswersBean> search_answer(String input_answer) throws Exception {
		if (con == null) {
			setConnection();
		}
		// CorrectAnswers tableのidとquestions_id、answerを取得
		String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE answer = ? ";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, input_answer);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				// 一旦変数で受ける
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				// ListはCorrectAnswers型
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questions_id, answer);
				list.add(bean);
			}
			return list;
		} catch (Exception e) { 
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	
	/**
	 * 答えを登録する
	*/
	public void register_answers(int questions_id, String[] answers) throws Exception {
		if (con == null) {
			setConnection();
		}
		// Correct_answers table にデータを追加
		String sql = "INSERT INTO correct_answers (questions_id, answer, created_at) VALUES (?, ?, CURRENT_TIMESTAMP());";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql);) {
			// answersの要素数の分、SQLを実行
			for(int i = 0; i < answers.length; i++) {
				st.setInt(1, questions_id);
				st.setString(2, answers[i]);
				/** SQL 実行 **/
				st.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました");
		}	
	}
}
