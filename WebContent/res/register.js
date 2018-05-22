function doAjax(address){
	if(validate){
		fetchUsername(address)
	}
}

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


function test(input){
	var regex = RegExp('^[0-9a-zA-Z]+$');
	return regex.test(input);
}

function fetchUsername(address){
	var request = new XMLHttpRequest();
	
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var firstname = document.getElementById("firstname").value;
	var lastname = document.getElementById("lastname").value;
	var address = document.getElementById("address").value;
	var postalcode = document.getElementById("postalcode").value;
	var province = document.getElementById("province").value;
	var country = document.getElementById("country").value;
	var phone = document.getElementById("phone").value;
	
	var data ="username=" + username + "&password=" +password + "&firstname=" + firstname + "&lastname=" + lastname + "&address=" + address + "&postalcode=" + postalcode + "&province=" + province + "&country=" + country + "&phone=" + phone;
	alert(data);
	request.onreadystatechange = function()
	 {
		textHandler(request);
	 };
	 request.open("POST", address, true);
	 request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 request.send(data); 
}

function handler(request){
if (request.readyState == 4 && request.status == 200) {
		
		if (request.responseText.charAt(0) == 'R') {			//Registration successful
			document.getElementById("result").innerHTML = request.responseText;
		}
		else {			//registration not successful
			document.getElementById("result").innerHTML = request.responseText;
		}

	}
}