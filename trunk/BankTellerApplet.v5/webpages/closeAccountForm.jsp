<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>
	<head>
		<title>Closing an Account</title>
	</head>

	<body>
		<h1>Close an Account Form</h1>
			
		<form name="closeAccount" action="CloseAccount.do" method="POST">
			<myTags:inputIDTextBox/>
			
			<!-- Used to tell servlet to close account -->
			<input type="hidden" readOnly name=ActionType value="6" />
			
			<input type="submit" value="Close Account" />
		</form>
	</body>
</html>