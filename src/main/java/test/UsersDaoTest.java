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
	public void notGetUser() {
		try {
			UsersDao dao = new UsersDao();
			UsersBean user = dao.search_id(4);
			assertThat(user.getId(), is(0));
			assertNull(user.getPassword());
			assertNull(user.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("search_idメソッドに、登録されているuserIdを引数として渡した場合、ユーザーを取得できること")
	public void getUser() {
		try {
			UsersDao dao = new UsersDao();
			UsersBean user = dao.search_id(1);
			assertThat(user.getId(), is(1));
			assertThat(user.getName(), is("testuser"));
			assertThat(user.getPassword(), is("testtest"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
