function checkInject()
{
	var text = document.getElementById("review");
	var textval = text.value;
	textval = textval.replace(/</g, "&lt;").replace(/>/g, "&gt;");
	console.log(textval);
}