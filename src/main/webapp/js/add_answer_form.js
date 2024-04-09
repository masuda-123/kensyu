document.addEventListener("DOMContentLoaded", function() {
	const btn = document.querySelector(".btn");
	btn.addEventListener('click', function(){
		var child1 = document.createElement('li');
		var parent = document.querySelector('.answer_form_area');
		var text = document.createTextNode("答え:");
		parent.appendChild(child1);
  		
		var child2 = document.createElement('input');
		child2.type = 'text';
		child2.id = 'answer';
		child2.name = 'answer';
		child1.appendChild(text);
		child1.appendChild(child2);
	});
});