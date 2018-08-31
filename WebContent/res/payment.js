/**
 * 
 */

function test(input){
	var regex = RegExp('^[a-zA-Z]+ [a-zA-Z]+$');
	return regex.test(input);
}

function testing()
{
	var t1 = document.getElementById("name").value;
	var t2 = document.getElementById("cardnum").value;
	var t3 = document.getElementById("cardseccode").value;
	var t4 = document.getElementById("cardexp").value;
	
	console.log(t1 + "," + t2 + "," + t3 + "," + t4);
}


function getPost(address)
{
	var request = new XMLHttpRequest();
	var t1 = document.getElementById("name").value;
	var t2 = document.getElementById("cardnum").value;
	var t3 = document.getElementById("cardseccode").value;
	var t4 = document.getElementById("cardexp").value;
	var data = "name=" + t1 + "&cardnum=" + t2 + "&cardseccode=" + t3 + "&cardexp=" + t4;

	 request.onreadystatechange = function()
	 {
			handlerUserStats(request);
	 };
	 request.open("POST", address, true);
	 request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 request.send(data); 	
}

function handlerUserStats(request){
	 if ((request.readyState == 4) && (request.status == 200))
	 {
		 //var target = document.getElementById("result");
		 var result =  request.responseText;
		 console.log(result);
		 alert("Order Successfully Completed." + "\nPO #:" + result);
		 
		// target.innerHTML = "";
		 //target.appendChild(table);
	 }
}


/*can add more functions if necessary*/