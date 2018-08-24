/**
 * 
 */
function fetchResult(address, category){
	 var request = new XMLHttpRequest();
	
	 //var data="category=" + category + "&price=0";
	 
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
		 var result =  JSON.parse(request.responseText);
		 var table = buildTable(result);
		 target.innerHTML = "";
		 target.appendChild(table);
	 }
	}


function showCart(result)
{
	var i = 0;
	var table = document.createElement("TABLE");
	var row = document.createElement("TR");
	var tdata = document.createElement("TD");
	tdata.innterHTML = "Book title";
	row.appendChild(tdata);
	
	tdata= document.createElement("TD");
	tdata.innerHTML = "Quantity";
	row.appendChild(tdata);
	
	tdata= document.createElement("TD");
	tdata.innerHTML = "Price";
	row.appendChild(tdata);
	table.appendChild(row);
	
	for (i = 0; i < result.length; i++)
	{
		alert(i + " " + result.length);
		row = document.createElement("tr");
		
		tdata = document.createElement("td");
		tdata.innerHTML = result[i].name;
		row.appendChild(tdata);
		
		tdata = document.createElement("td");
		tdata.innerHTML = result[i].quantity;
		row.appendChild(tdata);
		
		tdata = document.createElement("td");
		tdata.innerHTML = "$" + result[i].price;
		row.appendChild(tdata);
		
		table.appendChild(row);
		/*row = document.createElement("tr");
		tdata = document.createElement("td");
		tdata.innerHTML = "rating: " + reviewdata[i].rating + " time: " + reviewdata[i].time;
		row.appendChild(tdata);
		table.appendChild(row);*/
	}
	
	return table;
}