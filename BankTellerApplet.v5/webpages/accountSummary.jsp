<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>
	<head>
		<title>Account Summary</title>
	</head>

	<body>
		<h1>Account Summary</h1>
		
		<strong>Details are as follows:-</strong><br>
		<strong>Account Number is </strong><c:out value='${account.accountNumber}' default='null'/><br>
		<strong>Account Name is </strong><c:out value='${account.name}' default='null'/><br>
		<strong>Balance is </strong>$<c:out value='${account.balance}' default='null'/><br>
		<strong>Overdraft limit is: </strong>$<c:out value='${account.overdraft}' default='null'/><br>
		<br>
		<myTags:mainMenuHyperlink/>
	</body>
</html>