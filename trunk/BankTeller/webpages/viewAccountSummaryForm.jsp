<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>

	<head>
		<title>View Account Summary</title>
	</head>

	<body>
		<h1>View Account Summary Form</h1>
			
		<form name="viewAccountSummary" action="ViewAccountSummary.do" method="GET">
			<myTags:inputIDTextBox/>
			
			<!-- Used to tell servlet to view one account -->
			<input type="hidden" readOnly name=ActionType value="7" />
			
			<input type="submit" value="View Account Summary" />
		</form>
	</body>

</html>