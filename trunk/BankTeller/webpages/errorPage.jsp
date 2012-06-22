<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<html>
	<head>
		<title>Error Encountered</title>
	</head>

	<body>
		<h1>ERROR!</h1>
		<c:out value='${errorMsg}' default='Error encountered!'/><br>
		<br>
		<myTags:mainMenuHyperlink/>
	</body>
</html>