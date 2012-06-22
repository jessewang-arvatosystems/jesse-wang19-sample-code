function show_alert(startStation, endStation, day, month, year) {
	var today= new Date();
	var selectedDate= new Date();
	selectedDate.setFullYear(year, month, day);
	
	if( selectedDate < today){
		alert("Please choose today's : " + today.getDate() + "-"+ (today.getMonth()+1)+ "-"+ today.getFullYear()+" or a future date.");
		return false;
	}
	else if (startStation == endStation) {
		alert("Please choose different stations!");
		return false;
	}
	else {
		return true;
	}
}
