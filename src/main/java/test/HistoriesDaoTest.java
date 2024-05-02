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
	@DisplayName("register_historyメソッドで、履歴が登録できること")
	void registerHistory() throws Exception {
		//登録されているuser_idを変数に格納
		int userId = 1;
		int point = 77;
		HistoriesDao hisDao = new HistoriesDao();
		//渡したuser_idと一致する履歴データを取得
		ArrayList<HistoriesBean> hisList= hisDao.search_userId(userId);
		//履歴を登録
		hisDao.register_history(userId, point);
		//登録後、再度渡したuser_idと一致する履歴データを取得
		ArrayList<HistoriesBean> hisList2 = hisDao.search_userId(userId);
		
		//登録前と後で、データが増えていることを確認
		assertThat(hisList2.size() - hisList.size(), is(1));
		//細心の履歴データが、先程登録した内容であることを確認
		assertThat(hisList2.get(hisList2.size() - 1).getPoint(), is(77));
	}
}
