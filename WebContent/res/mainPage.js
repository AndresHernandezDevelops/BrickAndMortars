function doAjax(address)
{
	if(validate())
		{
			fetchResult(address);
		}
}

function validate() {
	var errorMessage = "";
	var ok = true;
	var category = document.getElementById("category").value;
	if(category.trim() == "")
	{
		errorMessage = "invalid category!";
		ok = false;
	}
	if(!ok)
		alert(errorMessage);
	return ok;
}

function fetchResult(address){
	 var request = new XMLHttpRequest();
	 var category = document.getElementById("category").value;

	 var searchByCategory = document.getElementById("searchByCategory").value;
	 
	 var data="bID=null&title=null&category=" + category + "&price=0&searchByCategory=" + searchByCategory;

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