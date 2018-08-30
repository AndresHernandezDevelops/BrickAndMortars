/**
 * 
 */
function isEmpty(obj){
	for(var key in obj){
		if(obj.hasOwnProperty(key)){
			return false;
		}
	}
	return true;
}

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
		 
		 var result =  JSON.parse(request.responseText);
		 
	 }
	}


function showCart(cart)
{
	var i = 0;
	var table = document.createElement("TABLE");
	table.setAttribute("class", "cartTable");
	var row = document.createElement("TR");
	var tdata = document.createElement("TD");
	

	for (i = 0; i < cart.length; i++)
	{
		var total = 0;
		var j = 0;
		//alert(i + " " + cart.length);
		row = document.createElement("tr");
		
		tdata = document.createElement("td");
		tdata.innerHTML = cart[i].title;
		row.appendChild(tdata);
		
		tdata = document.createElement("td");
		tdata.innerHTML = "1";
		row.appendChild(tdata);
		
		tdata = document.createElement("td");
		tdata.innerHTML = "$" + cart[i].price;
		row.appendChild(tdata);
		for(j=0; j<cart.length; j++){
			p = cart[j].price;
			total += p;
		}		
		
		/*tdata = document.createElement("td");
		tdata.innerHTML = "<button id=\"remove\"> X </button>";
		row.appendChild(tdata);*/
		
		table.appendChild(row);
		
	}
	row = document.createElement("tr");
	tdata = document.createElement("td");
	tdata.innerHTML = " ";
	row.appendChild(tdata);
	tdata = document.createElement("td");
	tdata.innerHTML = "TOTAL:";
	row.appendChild(tdata);
	tdata = document.createElement("td");
	tdata.innerHTML = "$" + total;
	row.appendChild(tdata);
	table.appendChild(row);
	
	var target = document.getElementById("result");
	target.appendChild(table);
}