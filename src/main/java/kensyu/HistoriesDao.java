package kensyu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	public List<HistoriesBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		// historiesからidとuser_idとpointを取得（条件：deleted_atが空であること）
		String sql = "SELECT id, name, password FROM users WHERE deleted_at is null";
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()){
			/** select文の結果をArrayListに格納 **/
			List<HistoriesBean> list = new ArrayList<HistoriesBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int point = rs.getInt("point");;
				HistoriesBean bean = new HistoriesBean(id, user_id, point);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}

}
