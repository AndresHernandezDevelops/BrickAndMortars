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

function getUserStats(address)
{
	var request = new XMLHttpRequest();
	 
	 var data="UBStats=true";

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
		 var target = document.getElementById("result");
		 var result =  JSON.parse(request.responseText);
		 var table = buildTable(result);
		 table.border = '1';
		 target.innerHTML = "";
		 target.appendChild(table);
	 }
}

function buildTable(result){
	var table = document.createElement("TABLE");
	table.setAttribute("id", "myTable");
    var row = document.createElement("TR");
    var data = document.createElement("TD");
    data.innerHTML = "User";
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = "Zip";
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = "Spent";
    row.appendChild(data);
    
    table.appendChild(row);
    
    var i=0;
    for (i=0; i<result.length; i++){
    	
    row = document.createElement("TR");
    
    data = document.createElement("TD");
    data.innerHTML =result[i].username;
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = result[i].zip;
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = result[i].spent;
    row.appendChild(data);
    
    table.appendChild(row);
    }
    
    return table;
}