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
	 * histories テーブルを全件取得
	 */
	public ArrayList<HistoriesBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		// historiesからidとuser_idとpointを取得（条件：deleted_atが空であること）
		String sql = "SELECT id, user_id, point, created_at FROM histories WHERE deleted_at is null";
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()){
			/** select文の結果をArrayListに格納 **/
			ArrayList<HistoriesBean> list = new ArrayList<HistoriesBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int point = rs.getInt("point");
				Timestamp created_at = rs.getTimestamp("created_at");
				HistoriesBean bean = new HistoriesBean(id, user_id, point, created_at);
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
