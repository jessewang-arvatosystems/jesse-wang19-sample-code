<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>

	<head>
		<title>Withdrawing Money</title>
	</head>

	<body>
		<h1>Withdrawing Money from Account Form</h1>
			
		<form name="updateAccount" action="UpdateAccount.do" method="POST">
			<myTags:inputIDTextBox/>
			<strong>Withdraw amount: </strong><input type="text" name="amount" /><br>
			
			<!-- Used to tell servlet to withdraw account -->
			<input type="hidden" readOnly name=ActionType value="4" />
			
			<input type="submit" value="Submit" />
		</form>
	</body>

</html>