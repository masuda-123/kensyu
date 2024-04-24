package test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kensyu.UsersBean;
import kensyu.UsersDao;

class UsersDaoTest {

	@Test
	@DisplayName("登録されていないuserIdを引数として渡した場合、戻り値にidやユーザー名、パスワードがセットされない")
	public void notSetField() {
		try {
			UsersDao dao = new UsersDao();
			UsersBean user = dao.search_id(4);
			assertThat(user.getId(), is(0));
			assertThat(user.getPassword(), nullValue());
			assertThat(user.getName(), nullValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("登録されているuserIdを引数として渡した場合、戻り値に該当するidやユーザー名、パスワードがセットされる")
	public void setField() {
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
