<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>
	<head>
		<title>View Accounts</title>
	</head>

	<body>
		<h1>Account View</h1>
		<table border="1" cellpadding="5">
			<tr>
				<td><strong>ACCOUNT</strong></td>
				<td><strong>TYPE</strong></td>
				<td><strong>NAME</strong></td>
				<td><strong>BALANCE</strong></td>
				<td><strong>OVERDRAFT LIMIT</strong></td>
			</tr>
			<c:forEach var="accountList" items="${list}" >
				<tr>
					<td><c:out value='${accountList.accountNumber}' default='null'/></td>
					<td><c:out value='${accountList.accountType}' default='null'/></td>
					<td><c:out value='${accountList.name}' default='null'/></td>
					<td>$<c:out value='${accountList.balance}' default='null'/></td>
					<td>$<c:out value='${accountList.overdraft}' default='null'/></td>
				</tr>
			</c:forEach> <br>
		</table>
		<br>
		<myTags:mainMenuHyperlink/>
	</body>
</html>