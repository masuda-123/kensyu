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
	public List<UserBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// userwsからidとnameとpasswordを取得（条件：deleted_atが空であること）
			String sql = "SELECT id, name, password FROM users WHERE deleted_at is null";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<UserBean> list = new ArrayList<UserBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				UserBean bean = new UserBean(id, name, pass);
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
	public UserBean search_userid(int id) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// Users tableのidとpassword、nameを取得
			String sql = "SELECT id, name, password FROM users WHERE deleted_at is null and id = ? ";
			
		
			
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			UserBean list = new UserBean();
			while (rs.next()) {
				// 一旦変数で受ける
				int userid = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				// ListはUserBean型
				list = new UserBean(userid, name, pass);
			
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