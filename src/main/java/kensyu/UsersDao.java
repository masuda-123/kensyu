package kensyu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends ConnectionDao {

	/**
	 * コンストラクタ（戻り値のない）
	 */
	// 初期値化
	public UsersDao() throws Exception {
		setConnection();
	}

	/**
	 * users テーブルを全件取得
	 */
	public List<UsersBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		// usersからidとnameとpasswordを取得（条件：deleted_atが空であること）
		String sql = "SELECT id, name, password FROM users WHERE deleted_at is null";
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()){
			/** select文の結果をArrayListに格納 **/
			List<UsersBean> list = new ArrayList<UsersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				UsersBean bean = new UsersBean(id, name, pass);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	/**
	 * 指定IDのレコードを取得する
	 */
	// UserBean型 idを引数として受け取る
	public UsersBean search_userId(int id) throws Exception {
		if (con == null) {
			setConnection();
		}
		// Users tableのidとpassword、nameを取得
		String sql = "SELECT id, name, password FROM users WHERE deleted_at is null and id = ? ";
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {	
			st.setInt(1, id);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			UsersBean list = new UsersBean();
			while (rs.next()) {
				// 一旦変数で受ける
				int userId = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				// ListはUserBean型
				list = new UsersBean(userId, name, pass);
			
			}
			return list;
		} catch (Exception e) { 
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
}
