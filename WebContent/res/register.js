function validate(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var lengthValidator = ((password.length<=30) && (username.length<=30));
	var bothPasswordsSame = check_pw();
	var ok = test(username) && test(password);
	if(!lengthValidator)
		alert("username or password too long!");
	if(!bothPasswordsSame)	
		alert("passwords dont match!!");
	if(!ok)
		alert("numbers and letters only please");
	hashPW(password);
	return totalBoolean = lengthValidator && ok && bothPasswordsSame;
}

function check_pw(){
	var ok = false;
	var pw = document.getElementById("password").value;
	var pwconf = document.getElementById("pwconf").value;
	if ((pwconf === pw)){
	//	alert("Passwords don't match!");
		ok = true;
	}
	return ok;
}

function hashPW(password){
	var hash = "";
	for(var i = 0; i < password.length; i++){
		hash += password.charCodeAt(i);
	}
	document.getElementById("password").value = hash;
	//alert(hash);
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

