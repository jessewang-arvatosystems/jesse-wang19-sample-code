
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<title>Checkout Ticket</title>
	<link rel="shortcut icon" href="./images/icon.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="./css/underground1.css" />
</head>
<body>
		<%@ include file="header.jsp" %>
		<img id="banner" src="./images/bannerj.JPG" />
		<br>
		<h1>Ticket:</h1>
		<div id="showTicket" >
			<p>
				<span id="bold">Start Station:</span>  
					${checkout.startStation}
			</p>
			<p>
				<span id="bold">End Station:</span>  
					${checkout.endStation}
			</p>
			<br>
			<p id="bold">Route:</p> 
			<div id="textbox"> ${checkout.route[0]} </div>
			<br>
			<span id="bold">Zone:</span>  ${checkout.zoneNumber} 
			<br>
			<br>
			<span id="bold">Departure Date:</span> 
						<span>Day: ${checkout.travelDay}   </span>
						<span>Month: ${checkout.travelMonth}   </span>  
						<span>Year: ${checkout.travelYear}   </span>
			<br>
			<br>
			<table>
				<tr>
					<th>Ticket Type</th>
					<th>Price per Ticket</th>
					<th>Passengers</th>
					<th>Subtotal</th>
				</tr>
				<tr>
					<td>Adult</td>
					<td> &#163; ${checkout.adultPrice}</td>
					<td> ${checkout.numOfAdults}</td>
					<td>&#163; ${checkout.adultSubTotal} </td>
				</tr>  
				<br>
				<tr>
					<td>Junior</td>
					<td>&#163; ${checkout.juniorPrice}</td>
					<td>${checkout.numOfJuniors}</td>
					<td>&#163; ${checkout.juniorSubTotal} </td> 
				</tr>
				<tr>
					<td>Senior</td>
					<td>&#163; ${checkout.seniorPrice}</td>
					<td> ${checkout.numOfSeniors} </td>
					<td>&#163; ${checkout.seniorSubTotal} </td>
				</tr>
			</table>
			<br>
			<span id="bold">Total Ticket Price: </span>&#163; ${checkout.totalPrice}
			<br>
			<br>
			<br>
			<span id="bold">Username: </span> ${userID}
			<br>
			<br>
			<form action="buyAndBuildTickets.htm" method="POST" >
				<select name="paymentType">
					<option value="Credit">Credit Card</option>
					<option value="Debit">Debit Card</option>
					<option value="Cash">Cash</option>
				</select>
		
				<input type="hidden" name="startStation" value="${checkout.startStation}" />
				<input type="hidden" name="endStation" value="${checkout.endStation}" />
				<input type="hidden" name="route" value="${checkout.route}" />
				<input type="hidden" name="zoneNumber" value="${checkout.zoneNumber}" />
				<input type="hidden" name="day_id" value="${checkout.travelDay}" />	
				<input type="hidden" name="month_id" value="${checkout.travelMonth}" />
				<input type="hidden" name="year_id" value="${checkout.travelYear}" />
				<input type="hidden" name="numberAdults" value="${checkout.numOfAdults}" />
				<input type="hidden" name="numberJuniors" value="${checkout.numOfJuniors}" />
				<input type="hidden" name="numberSeniors" value="${checkout.numOfSeniors}" />
				<input type="hidden" name="adultPrice" value="${checkout.adultPrice}" />
				<input type="hidden" name="juniorPrice" value="${checkout.juniorPrice}" />
				<input type="hidden" name="seniorPrice" value="${checkout.seniorPrice}" />
				<input type="hidden" name="totalPrice" value="${checkout.totalPrice}" />
				<input type="hidden" name="userid" value="${userID}" />
				<input type="submit" name="submit" value="CONFIRM BUY">
			</form>
			<br>
			<a href="buyTickets.htm">Go Back to Buy Ticket</a>
		</div>
</body>
</html>