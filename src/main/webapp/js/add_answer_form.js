function addForm() {	
		var answer_form = document.createElement('div');
		answer_form.className = "answer_form";
		var input = document.createElement('input');
		input.type = 'text';
		input.id = 'answer';
		input.name = 'answer';
		var button = document.createElement('button');
		button.type = 'button';
		button.addEventListener('click', deleteForm);
		var text = document.createTextNode('削除');
		
		var parent = document.querySelector('.answer_form_list');
		parent.appendChild(answer_form)
		answer_form.appendChild(input);
		answer_form.appendChild(button);
		button.appendChild(text);
};