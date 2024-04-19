function addForm() {
		//答えのフォームとボタンを含むdivタグを作成
		var answer_form_area = document.createElement('div');
		answer_form_area.className = "answer_form_area"
		answer_form_area.id = "answer_form_area" + ++i;
		//答えの入力フォームを作成
		var input = document.createElement('input');
		input.type = 'text';
		input.id = 'answer';
		input.name = 'answer';
		//削除ボタンを作成
		var delete_btn = document.createElement('button');
		delete_btn.type = 'button';
		delete_btn.setAttribute('onclick', `deleteForm(${answer_form_area.id})`);
		var text = document.createTextNode('削除');
		
		//答えのフォームリストを含むタグを取得
		var parent = document.querySelector('.answer_form_list');
		
		//フォームリストの中にdivタグを追加
		parent.appendChild(answer_form_area);
		//divタグの中に答えの入力フォームを追加
		answer_form_area.appendChild(input);
		//divタグの中に削除ボタンを追加
		answer_form_area.appendChild(delete_btn);
		//削除ボタンにテキストを追加
		delete_btn.appendChild(text);
};