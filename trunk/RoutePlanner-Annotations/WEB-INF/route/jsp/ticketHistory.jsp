<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Calendar" %>

<html>
<head>
	<link rel="shortcut icon" href="./images/icon.ico" type="image/x-icon" />
	<title>Transaction History</title>
	<link rel="stylesheet" type="text/css" href="./css/underground1.css" />
</head>
<body>
	<%@ include file="header.jsp" %>
	<img id="banner" src="./images/bannerj.JPG" />
	<br/>

	<table border="1" bordercolor=red align=CENTER>
	<tr>
		<th>Ticket ID</th>
		<th>Start Station</th>
		<th>End Station</th>
		<th>Ticket Type</th>
		<th>Ticket Price</th>
		<th>Travel Date</th>
		
	</tr>
	<c:forEach var="ticket" items="${ticketList}">
		<tr>
			<td>${ticket.ticketID}</td>
			<td>${ticket.startStation}</td>
			<td>${ticket.endStation}</td>
			<td>${ticket.ticketType}</td>
			<td>&#163; ${ticket.price}</td>
			<td>${ticket.travelDate.time}</td>
		</tr>
	</c:forEach>
	</table>
	
	<a href="transactionHistory.htm">Go Back to Transaction History</a>

</body>
</html>