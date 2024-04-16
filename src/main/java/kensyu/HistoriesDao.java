package kensyu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class HistoriesDao extends ConnectionDao {
	/**
	 * コンストラクタ（戻り値のない）
	 */
	// 初期値化
	public HistoriesDao() throws Exception {
		setConnection();
	}
	
	/**
	 * 指定IDのレコードを取得する
	 */
	public ArrayList<HistoriesBean> search_userId(int currentUserId) throws Exception {
		if (con == null) {
			setConnection();
		}
		// Histories tableのidとuser_id,point,created_atを取得
		String sql = "SELECT id, user_id, point, created_at FROM histories WHERE deleted_at is null and user_id = ? ";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {	
			st.setInt(1, currentUserId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			ArrayList<HistoriesBean> list = new ArrayList<HistoriesBean>();
			while (rs.next()) {
				// 一旦変数で受ける
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int point = rs.getInt("point");
				Timestamp createdAt = rs.getTimestamp("created_at");
				// ListはUserBean型
				HistoriesBean bean = new HistoriesBean(id, userId, point, createdAt);
				list.add(bean);
			}
			return list;
		} catch (Exception e) { 
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	
	/**
	 * 履歴を登録する
	 */
	public void register_history(int userId, int point) throws Exception {
		if (con == null) {
			setConnection();
		}
		// Histories table にデータを追加
		String sql = "INSERT INTO histories (user_id, point, created_at) VALUES (?, ?,  CURRENT_TIMESTAMP());";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, userId);
			st.setInt(2, point);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました");
		}
	}

}
