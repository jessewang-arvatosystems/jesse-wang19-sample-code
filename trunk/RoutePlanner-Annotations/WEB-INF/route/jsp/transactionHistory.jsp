<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.Calendar" %>

<html>
<head>
	<link rel="shortcut icon" href="./images/icon.ico" type="image/x-icon" />
	<title>Transaction History</title>
	<link rel="stylesheet" type="text/css" href="./css/underground1.css" />
</head>
<body>
	<%@ include file="header.jsp" %>
	<img id="banner" src="./images/bannerj.JPG"/>
	<br/>

	<c:if test="${fn:length(transactionList) == '0' }">
		No transaction history found for ${userID}!
	</c:if>

	<c:if test="${fn:length(transactionList) > '0' }">
	<table id="table" align=CENTER>
		<tr>
		<th>Transaction ID</th>
		<th>Payment Type</th>
		<th>Total Price</th>
		<th>Purchase Date</th>
		</tr>
		<c:forEach var="transaction" items="${transactionList}">
		<tr>
			<form method="POST" action = "ticketHistory.htm">
			<input type="hidden" name="transactionID" value=${transaction.transactionID}>
			<td><input type="SUBMIT" value="ID-${transaction.transactionID}"></td>
			</form>
			<td>${transaction.paymentType}</td>
			<td>&#163; ${transaction.totalPrice}</td>
			<td>${transaction.purchaseDate.time}</td>
		</tr>
		</c:forEach>
	</table>
	</c:if>

	

</body>
</html>