/**
 * 
 */
var click=0;
function clicks(){
	click+=1;
	if(click % 3 == 0){
		alert("Payment Denied. Try again.");
	}
	else{
		alert("Purchase Confirmed. Return to Home to browse for more");
	}
}
/*can add more functions if necessary*/