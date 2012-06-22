<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>

	<head>
		<title>Depositing Money</title>
	</head>

	<body>
		<h1>Depositing Money from Account Form</h1>
			
		<form name="updateAccount" action="UpdateAccount.do" method="POST">
			<myTags:inputIDTextBox/>
			<strong>Deposit amount: </strong><input type="text" name="amount" /><br>
			
			<!-- Used to tell servlet to deposit account -->
			<input type="hidden" readOnly name=ActionType value="3" />
			
			<input type="submit" value="Submit" />
		</form>
	</body>

</html>