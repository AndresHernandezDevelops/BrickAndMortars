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
		 target.innerHTML = request.responseText;
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
		 target.innerHTML = request.responseText;
	 }
	}