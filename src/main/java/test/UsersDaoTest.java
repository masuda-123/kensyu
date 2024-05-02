package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kensyu.UsersBean;
import kensyu.UsersDao;

class UsersDaoTest {

	@Test
	@DisplayName("search_idメソッドに、登録されていないuserIdを引数として渡した場合、ユーザーが取得できないこと")
	public void notGetUser() throws Exception {
		UsersDao dao = new UsersDao();
		//登録されていないidを渡して、ユーザーを取得しようとする
		UsersBean user = dao.search_id(4);
		
		//答えが取得できないことを確認
		assertThat(user.getId(), is(0));
		assertNull(user.getPassword());
		assertNull(user.getName());
	}
	
	@Test
	@DisplayName("search_idメソッドに、登録されているuserIdを引数として渡した場合、ユーザーを取得できること")
	public void getUser() throws Exception {
		UsersDao dao = new UsersDao();
		//登録されているidを渡して、ユーザーを取得
		UsersBean user = dao.search_id(1);
		
		//ユーザーが取得できることを確認
		assertThat(user.getId(), is(1));
		assertThat(user.getName(), is("testuser"));
		assertThat(user.getPassword(), is("testtest"));
	}

}
