package kensyu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CorrectAnswersDao extends ConnectionDao {
	/**
	 * コンストラクタ（戻り値のない）
	 */
	public CorrectAnswersDao() throws Exception {
		//DBと接続する
		setConnection();
	}

	/**
	 * correct_answers テーブルを全件取得
	 */
	public ArrayList<CorrectAnswersBean> findAll() throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		// correct_answers の全てのレコードからidとquestions_id、answerを取得
		String sql = "SELECT id, questions_id, answer FROM correct_answers";
		
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
			/** select文の結果をArrayListに格納 **/
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				//CorrectAnswersBeanオブジェクトを作成し、コンストラクタに値を渡す
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questions_id, answer);
				//listにオブジェクトを追加
				list.add(bean);
			}
			//listを返す（実行結果があればオブジェクトが格納されているが、実行結果がなければ空）
			return list;
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	
	/**
	 *  指定questions_idのレコードを取得する
	 */
	public ArrayList<CorrectAnswersBean> search_questions_id(int questionId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers から、questions_id が一致するレコードのidとquestions_id、answerを取得
		String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE questions_id = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの?に値をセット
			st.setInt(1, questionId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				//CorrectAnswersBeanオブジェクトを作成し、コンストラクタに値を渡す
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questions_id, answer);
				//listにオブジェクトを追加
				list.add(bean);
			}
			//listを返す（実行結果があればオブジェクトが格納されているが、実行結果がなければ空）
			return list;
		} catch (Exception e) { 
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	
	/**
	 *  指定answerのレコードを取得する
	 */
	public ArrayList<CorrectAnswersBean> search_answer(String input_answer) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers から、answer が一致するレコードのidとquestions_id、answerを取得
		String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE answer = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setString(1, input_answer);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			
			//実行結果を1行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				//CorrectAnswersBeanオブジェクトを作成し、コンストラクタに値を渡す
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questions_id, answer);
				//listにオブジェクトを追加
				list.add(bean);
			}
			//listを返す（実行結果があればオブジェクトが格納されているが、実行結果がなければ空）
			return list;
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	
	/**
	 * 答えを登録する
	*/
	public void register_answers(int questionId, String[] answers) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers にデータを追加
		String sql = "INSERT INTO correct_answers (questions_id, answer, created_at) VALUES (?, ?, CURRENT_TIMESTAMP());";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			// answersの要素数の分、SQLを実行
			for(String answer : answers) {
				//sqlの ? に値をセット
				st.setInt(1, questionId);
				st.setString(2, answer);
				/** SQL 実行 **/
				st.executeUpdate();
			}
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの登録に失敗しました");
		}	
	}
	
	/**
	 * 答えを削除する
	*/
	public void delete_answers(int questionId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers の question_id が一致しているデータを削除
		String sql = "DELETE FROM correct_answers WHERE questions_id = ?";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			insert_st.setInt(1, questionId);
			/** SQL 実行 **/
			insert_st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの削除に失敗しました");
		}
	}
	
	/**
	 * 答えを１件更新する
	*/
	public void update_answer(int answerId, String answer) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers の id が一致しているデータを更新
		String sql = "UPDATE correct_answers SET answer = ?, updated_at = CURRENT_TIMESTAMP() WHERE id = ?;";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setString(1, answer);
			st.setInt(2, answerId);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの更新に失敗しました");
		}	
	}
	
	/**
	 * 答えを１件登録する
	*/
	public void register_answer(int questionId, String answer) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers にデータを追加
		String sql = "INSERT INTO correct_answers (questions_id, answer, created_at) VALUES (?, ?, CURRENT_TIMESTAMP());";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setInt(1, questionId);
			st.setString(2, answer);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの登録に失敗しました");
		}	
	}
	
	/**
	 * 答えを１件削除する
	*/
	public void delete_answer(int answerId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers の id が一致しているデータを削除
		String sql = "DELETE FROM correct_answers WHERE id = ?";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			insert_st.setInt(1, answerId);
			/** SQL 実行 **/
			insert_st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの削除に失敗しました");
		}
	}
}
