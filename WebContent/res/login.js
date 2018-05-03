function validate(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var ok = test(username) && test(password);
	if(!ok)
		alert("numbers and letters only please");
	return ok;
}


function check_pw(){
	var ok = true;
	var pw = document.getElementById("password").value;
	var pwconf = document.getElementById("pwconf").value;
	if (!(pwconf === pw)){
		alert("Passwords don't match!");
		ok = false;
	}
	return ok;
}


function test(input){
	var regex = RegExp('^[0-9a-zA-Z]+$');
	return regex.test(input);
}


function copy()
{
	var regex = RegExp('^[0-9a-zA-Z]*@[0-9a-zA-Z]*.com$');
	var t1 = "test";
	var t2 = "@#$";
	var t3 = "jyk1961@hotmail.com"
	console.log(regex.test(t1));
	console.log(regex.test(t2));
	console.log(regex.test(t3));
}

