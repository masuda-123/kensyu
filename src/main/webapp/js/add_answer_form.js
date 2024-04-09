document.addEventListener("DOMContentLoaded", function() {
	const btn = document.querySelector(".btn");
	btn.addEventListener('click', function(){
		var child = document.createElement('input');
		child.type = 'text';
		child.id = 'answer';
		child.name = 'answer';
		var parent = document.querySelector('.answer_form');
		parent.appendChild(child);
	});
});