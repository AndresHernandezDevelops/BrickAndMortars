/**
 * 
 */
function showCart(cartjson)
{
	var i = 0;
	var table = document.createElement("table");
	for (i = 0; i < cartjson.length; i++)
	{
		alert(i + " " + cartjson.length);
		var row = document.createElement("tr");
		var tdata = document.createElement("td");
		tdata.innerHTML = cartjson[i].name;
		row.appendChild(tdata);
		table.appendChild(row);
		
		row = document.createElement("tr");
		tdata = document.createElement("td");
		tdata.innerHTML = cartjson[i].price;
		row.appendChild(tdata);
		table.appendChild(row);
		
		/*row = document.createElement("tr");
		tdata = document.createElement("td");
		tdata.innerHTML = "rating: " + reviewdata[i].rating + " time: " + reviewdata[i].time;
		row.appendChild(tdata);
		table.appendChild(row);*/
	}
	
	var loc = document.getElementById("result");
	loc.appendChild(table);
}