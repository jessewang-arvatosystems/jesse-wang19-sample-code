<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Buy Ticket</title>
	<link rel="shortcut icon" href="./images/icon.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="./css/underground1.css" />
	<script type="text/javascript" src="./js/calendar/calendar.js"></script>
	<script language="Javascript">
					
		function testReturn(amountAdult, amountSenior, amountJunior){
			var totalTickets = amountAdult + amountSenior + amountJunior;
			if(totalTickets >= 1) {
				return true;
			} else {
				alert("You must buy at least one ticket!");
				return false;
			}
		}
		<%@ include file="verifyStartAndEndStations.jsp" %>
	</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<img id="banner" src="./images/bannerj.JPG" />
	<br>

	<div id="buyticket">
	<p id="bold">Buy a ticket:</p>
	
	<form action="showTicket.htm" method="POST" name="BuyTicket" 
		onsubmit="return testReturn(numberAdults.value, numberSeniors.value, numberJuniors.value)">

		<p id="alignright"> Start Station:
			<select name="startStationBuy">
				<c:forEach var="stationName" items="${stationList}">
					<c:choose>
						<c:when test="${stationName eq sessionScope.persistentStart}">
							<option value="${stationName}" selected>${stationName}</option>
						</c:when>
						<c:otherwise>
							<option value="${stationName}">${stationName}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach> 
		  	</select>
		</p>
		<p id="alignright"> End Station:
			<select name="endStationBuy">
				<c:forEach var="stationName" items="${stationList}">
					<c:choose>
						<c:when test="${stationName eq sessionScope.persistentEnd}">
							<option value="${stationName}" selected>${stationName}</option>
						</c:when>
						<c:otherwise>
							<option value="${stationName}">${stationName}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach> 
	      	</select>	
		</p>

		<br>
			<p>
				<script>DateInput('orderdate', true, 'DD-MON-YYYY')</script>
			</p>
		<br>
		<p id="bold">Number of Passengers</p>
		<p> Adult(16-59):
			<select name="numberAdults">
				<option value="0" selected>0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</p>
		<p>Junior(0-15):
			<select name="numberJuniors" tabindex="5">
				<option value="0" selected>0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</p>
		<p>Senior(60+):
			<select name="numberSeniors" tabindex="6">
					<option value="0" selected>0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
			</select>
		</p>
	
		<input type="Submit" onclick="return show_alert(startStationBuy.value, 
														endStationBuy.value, 
														day_id.value, 
														month_id.value, 
														year_id.value)" 
														value="Calculate Ticket Information"/>
	</form>


	</div>


</body>
</html>	