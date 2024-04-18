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
	 *  指定questions_idのレコードを取得する
	 */
	public ArrayList<CorrectAnswersBean> search_questions_id(int questionId) throws Exception {
		if (con == null) {
			setConnection();
		}
		// CorrectAnswers tableのidとquestions_id、answerを取得
		String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE questions_id = ? ";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, questionId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				// 一旦変数で受ける
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
	 *  指定answerのレコードを取得する
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
	public void register_answers(int questionId, String[] answers) throws Exception {
		if (con == null) {
			setConnection();
		}
		// correct_answers table にデータを追加
		String sql = "INSERT INTO correct_answers (questions_id, answer, created_at) VALUES (?, ?, CURRENT_TIMESTAMP());";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql);) {
			// answersの要素数の分、SQLを実行
			for(String answer : answers) {
				st.setInt(1, questionId);
				st.setString(2, answer);
				/** SQL 実行 **/
				st.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました");
		}	
	}
	
	/**
	 * 答えを削除する
	*/
	public void delete_answers(int questionId) throws Exception {
		if (con == null) {
			setConnection();
		}
		// Questions table のデータを削除
		String sql = "DELETE FROM correct_answers WHERE questions_id = ?";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(sql);) {
			insert_st.setInt(1, questionId);
			/** SQL 実行 **/
			insert_st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの削除に失敗しました");
		}
	}
	
	/**
	 * 答えを１件更新する
	*/
	public void update_answer(int answerId, String answer) throws Exception {
		if (con == null) {
			setConnection();
		}
		// correct_answers table のデータを更新
		String sql = "UPDATE correct_answers SET answer = ? WHERE id = ?;";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, answer);
			st.setInt(2, answerId);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの更新に失敗しました");
		}	
	}
	
	/**
	 * 答えを１件登録する
	*/
	public void register_answer(int questionId, String answer) throws Exception {
		if (con == null) {
			setConnection();
		}
		// correct_answers table にデータを追加
		String sql = "INSERT INTO correct_answers (questions_id, answer, created_at) VALUES (?, ?, CURRENT_TIMESTAMP());";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql);) {
			// answersの要素数の分、SQLを実行
			st.setInt(1, questionId);
			st.setString(2, answer);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました");
		}	
	}
	
	/**
	 * 答えを１件削除する
	*/
	public void delete_answer(int answerId) throws Exception {
		if (con == null) {
			setConnection();
		}
		// Questions table のデータを削除
		String sql = "DELETE FROM correct_answers WHERE id = ?";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(sql);) {
			insert_st.setInt(1, answerId);
			/** SQL 実行 **/
			insert_st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの削除に失敗しました");
		}
	}
}
