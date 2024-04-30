package test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kensyu.HistoriesBean;
import kensyu.HistoriesDao;

class HistoriesDaoTest {

	@Test
	@DisplayName("register_historyメソッドで、答えが登録できること")
	void registerHistory() {
		try {
			int userId = 1;
			int point = 77;
			HistoriesDao hisDao = new HistoriesDao();
			ArrayList<HistoriesBean> hisList= hisDao.search_userId(userId);
			hisDao.register_history(userId, point);
			ArrayList<HistoriesBean> hisList2 = hisDao.search_userId(userId);
			assertThat(hisList2.size() - hisList.size(), is(1));
			assertThat(hisList.get(hisList.size() - 1).getPoint(), is(77));
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
