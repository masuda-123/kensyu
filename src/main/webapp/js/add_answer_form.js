var i = 0;

function addForm() {	
		var answer_form = document.createElement('div');
		answer_form.className = "answer_form"
		answer_form.id = "answer_form" + ++i;
		var input = document.createElement('input');
		input.type = 'text';
		input.id = 'answer';
		input.name = 'answer';
		var delete_btn = document.createElement('button');
		delete_btn.type = 'button';
		delete_btn.setAttribute('onclick', `deleteForm(${answer_form.id})`);
		var text = document.createTextNode('削除');
		
		var parent = document.querySelector('.answer_form_list');
		parent.appendChild(answer_form)
		answer_form.appendChild(input);
		answer_form.appendChild(delete_btn);
		delete_btn.appendChild(text);
};