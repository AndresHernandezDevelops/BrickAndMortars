/**
 * 
 */


function getPost(address)
{
	var request = new XMLHttpRequest();

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
		 alert("Order Successfully Completed." + "\nPO #:" + result);
		 
		// target.innerHTML = "";
		 //target.appendChild(table);
	 }
}


/*can add more functions if necessary*/