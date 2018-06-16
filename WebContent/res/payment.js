/**
 * 
 */
var click=0;
function clicks(){
	click+=1;
	if(click % 3 == 0){
		alert("Credit Card Authorization Failed.");
	}
	else{
		alert("Order Successfully Completed.");
	}
}
/*can add more functions if necessary*/