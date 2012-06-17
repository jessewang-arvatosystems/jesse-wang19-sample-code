<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>

	<head>
		<title>New Savings Account Form</title>
	</head>

	<body>
		<h1>New Savings Account Form</h1>
			
		<form action="CreateAccount.do" method="POST">
			<myTags:accountNameAndDepositAmountBoxes/>
			
			<!-- Used to tell servlet to create savings account -->
			<input type="hidden" readOnly name=ActionType value="2" />
	
			<input type="submit" value="Make new account" />
		</form>
	</body>

</html>