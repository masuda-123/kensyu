package kensyu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersDao extends ConnectionDao {

	/**
	 * コンストラクタ（戻り値のない）
	 */
	public UsersDao() throws Exception {
		//DBと接続する
		setConnection();
	}

	/**
	 * users テーブルを全件取得
	 */
	public ArrayList<UsersBean> findAll() throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//users からidとnameとpasswordを取得（条件：deleted_atが空であること）
		String sql = "SELECT id, name, password FROM users WHERE deleted_at is null";
		
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()){
			/** select文の結果をArrayListに格納 **/
			ArrayList<UsersBean> list = new ArrayList<UsersBean>();
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				//オブジェクトを作成し、コンストラクタに値を渡す
				UsersBean bean = new UsersBean(id, name, pass);
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
	 * 指定IDのレコードを取得する
	 */
	public UsersBean search_id(int userId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//users からidとpassword、nameを取得（条件：idが一致すること、deleted_atが空であること）
		String sql = "SELECT id, name, password FROM users WHERE deleted_at is null and id = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setInt(1, userId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			UsersBean bean = new UsersBean();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				//UsersBean型のbeanに実行結果を格納
				bean.setId(id);
				bean.setName(name);
				bean.setPassword(pass);
			}
			//beanオブジェクトを返す
			return bean;
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
}
