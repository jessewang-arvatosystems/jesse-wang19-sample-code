<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>

	<head>
		<title>New Current Account Form</title>
	</head>

	<body>
		<h1>New Current Account Form</h1>
			
		<form action="CreateAccount.do" method="POST">
			<myTags:accountNameAndDepositAmountBoxes/>
			<strong>Overdraft amount: </strong><input type="text" name="overdraftAmount" /><br>
			
			<!-- Used to tell servlet to create current account -->
			<input type="hidden" readOnly name=ActionType value="1" />
	
			<input type="submit" value="Make new account" />
		</form>
	</body>

</html>