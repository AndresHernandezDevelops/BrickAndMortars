function doAjax(address, category)
{
	
	fetchResult(address, category);
}

function searchByTextAjax(address)
{
	if(textValidate())
		{
			textFetchResult(address);
		}
}

function textValidate(address)
{
	//alert("hit");
	var errorMessage = "";
	var ok = true;
	var searchByText = document.getElementById("searchByText").value;
	if(searchByText.trim() == "")
	{
		errorMessage = "invalid search string!";
		ok = false;
	}
	if(!ok)
		alert(errorMessage);
	return ok;
}

function fetchResult(address, category){
	 var request = new XMLHttpRequest();
	
	 var data="category=" + category + "&price=0";
	 
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


function textFetchResult(address){
	 var request = new XMLHttpRequest();

	 var searchByText = document.getElementById("searchByText").value;

	 var searchByTextButton = document.getElementById("searchByTextButton").value;
	 
	 var data="searchByText=" + searchByText + "&searchByTextButton=" + searchByTextButton;

	 request.onreadystatechange = function()
	 {
		textHandler(request);
	 };
	 request.open("POST", address, true);
	 request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 request.send(data); 
	} 

function textHandler(request){
	 if ((request.readyState == 4) && (request.status == 200))
	 {
		 var target = document.getElementById("result");
		 var result =  JSON.parse(request.responseText);
		 var table = buildTable(result);
		 target.innerHTML = "";
		 target.appendChild(table);
	 }
	}


function buildTable(result){
	var table = document.createElement("TABLE");
	table.setAttribute("id", "myTable");
	table.setAttribute("class", "resultTable");
    var row = document.createElement("TR");
    var data = document.createElement("TD");
    data.innerHTML = "book ID";
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = "title";
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = "category";
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = "price";
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = "thumbnail";
    row.appendChild(data);
    table.appendChild(row);
    
    var i=0;
    for (i=0; i<result.length; i++){
    	
    row = document.createElement("TR");
    
    data = document.createElement("TD");
    data.innerHTML =result[i].bID;
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = result[i].title;
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = result[i].category;
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = "$" + result[i].price;
    row.appendChild(data);
    
    data = document.createElement("TD");
    data.innerHTML = "<img src= " + result[i].thumbnail + "/>";
    row.appendChild(data);
    
    table.appendChild(row);
    }
    
    return table;
}

function checkInject()
{
	var text = document.getElementById("review");
	var textval = text.value;
	textval = textval.replace(/</g, "&lt;").replace(/>/g, "&gt;");
	console.log(textval);
}


//slideshow js
var imageIndex = 0;
var images = [];

var timeInterval = 5000;

images[0] = "https://i.imgur.com/6h4GZho.jpg";
images[1] = "https://i.imgur.com/EwiYP0P.jpg";
images[2] = "https://i.imgur.com/CiGEFOF.jpg";
images[3] = "https://i.imgur.com/9OI9Uw0.jpg";


function changeImg(){
	   document.slides.src = images[imageIndex];
	  //document.getElementById("text").innerHTML = titles[imageIndex];
	   
	   if(imageIndex < images.length - 1){
		   imageIndex++;
	   }
	   else{
		   imageIndex = 0;
	   }
	   setTimeout("changeImg()", timeInterval);
}

window.onload = changeImg;
