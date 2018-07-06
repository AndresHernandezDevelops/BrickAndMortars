function checkInject()
{
	var text = document.getElementById("review");
	var textval = text.value;
	textval = textval.replace(/</g, "&lt;").replace(/>/g, "&gt;");
	console.log(textval);
}

function insertReviews(reviewdata)
{
	var i = 0;
	var table = document.createElement("table");
	for (i = 0; i < reviewdata.length; i++)
	{
		alert(i + " " + reviewdata.length);
		var row = document.createElement("tr");
		var tdata = document.createElement("td");
		tdata.innerHTML = reviewdata[i].name;
		row.appendChild(tdata);
		table.appendChild(row);
		
		row = document.createElement("tr");
		tdata = document.createElement("td");
		tdata.innerHTML = reviewdata[i].review;
		row.appendChild(tdata);
		table.appendChild(row);
		
		row = document.createElement("tr");
		tdata = document.createElement("td");
		tdata.innerHTML = "rating: " + reviewdata[i].rating + " time: " + reviewdata[i].time;
		row.appendChild(tdata);
		table.appendChild(row);
	}
	
	var loc = document.getElementById("reviews");
	loc.appendChild(table);
}
