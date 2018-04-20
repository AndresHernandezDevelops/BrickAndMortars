function doSimpleAjax(address){
	 var request = new XMLHttpRequest();
	 var monthlyReport = document.getElementById("monthlyReport").value;
	 
	 var data="monthlyReport=" + monthlyReport;

	 request.onreadystatechange = function()
	 {
			handler(request);
	 };
	 request.open("POST", address, true);
	 request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 request.send(data); 
	} 

function handler(request){
	 if ((request.readyState == 4) && (request.status == 200))
	 {
		 var target = document.getElementById("result");
		 target.innerHTML = request.responseText;
	 }
}