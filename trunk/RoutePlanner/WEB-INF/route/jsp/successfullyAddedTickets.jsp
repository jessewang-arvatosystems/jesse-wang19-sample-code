<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Transaction Success</title>
	<link rel="shortcut icon" href="./images/icon.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="./css/underground1.css" />

</head>
<body>
	<%@ include file="header.jsp" %>
	<img id="banner" src="./images/bannerj.JPG" />
	<br>

	Successfully bought Tickets!
	<table id="ticket">
	<tr>
		<th>Ticket Number </th>
		<th> Start Station </th>
		<th> End Station </th>
		<th> Travel Date </th>
		<th> Ticket Type </th>
	</tr>
	<c:forEach var="ticket" items="${successBuyVO.tickets}">
		<tr>
		<td>${ticket.ticketID}</td>
		<td>${ticket.startStation}</td>
		<td>${ticket.endStation}</td>
		<td>${ticket.travelDate.time}</td>
		<td>${ticket.ticketType}</td>
		</tr>
	</c:forEach>
	</table>
	Bought a total of ${successBuyVO.totalTickets} tickets.<br>
	Transaction ID: ${successBuyVO.transactionID}<br>
	User ID: ${userID}

	<br><br>
	<FORM>
	<INPUT TYPE="BUTTON" VALUE="Home Page" ONCLICK="window.location.href='homepage.htm'">
	</FORM>


</body>
</html>
