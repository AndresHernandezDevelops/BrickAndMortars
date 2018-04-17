/**
 * actually meant to be for register
 */
function check_pw(){
var ok = true;
var pw = document.getElementById("pw").value;
var pwconf = document.getElementById("pwconf").value;
if (!(pwconf === pw)){
	alert("Passwords don't match!");
	ok = false;
}

return ok;
}