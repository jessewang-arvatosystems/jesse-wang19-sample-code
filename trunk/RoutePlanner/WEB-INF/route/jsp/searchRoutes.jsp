<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Search Routes</title>
	<link rel="shortcut icon" href="./images/icon.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="./css/underground1.css" />
	<script type="text/javascript">
		<%@ include file="verifyStartAndEndStations.jsp" %>
	</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<img id="banner" src="./images/bannerj.JPG" />
	<br>
	<form id = "searchform" action="printJourney.htm" method="POST">
		<table id="table">
			<tr>
				<td> <h2>Select Start Station:</h2> </td>
				<td> <h2>Select End Station:</h2> </td>
			</tr>
			<tr>
				<td> <img src="./images/topcircle.JPG"> </td>
				<td> <img src="./images/topcircle.JPG"> </td>
			</tr>
			<tr>
				<td id="circleSquare">
					<select name="startStation">
						<c:forEach var="stationName" items="${stationList}">
							<option value="${stationName}">${stationName}</option>
						</c:forEach> 
					</select>
				</td>
				<td id="circleSquare">
					<select name="endStation">
						<c:forEach var="stationName" items="${stationList}">
							<option value="${stationName}">${stationName}</option>
						</c:forEach> 
					</select>
				</td>
			</tr>
			<tr>
				<td> <img src="./images/bottomcircle.JPG"></td>
				<td><img src="./images/bottomcircle.JPG"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input id="align" type="Submit" onclick="return show_alert(startStation.value, endStation.value)" value="Go!" />
				<td>
			</tr>
		</table>
	</form>
</body>
</html>	