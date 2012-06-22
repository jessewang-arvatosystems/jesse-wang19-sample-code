<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<link rel="shortcut icon" href="./images/icon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="./css/underground1.css" />
		<TITLE> Route Results </TITLE>
	</HEAD>
	
	<BODY>
		<%@ include file="header.jsp" %>
	<img id="banner" src="./images/bannerj.JPG"/>
	<br>
		<table>
			  <c:forEach var="message" items="${resultList.message}" >
			     <tr>
			        <td>${message}</br></td>
			     </tr>
			  </c:forEach>
		</table>
		</br> 
		<a href="homepage.htm">Go to home page</a>	
	 		
	</BODY>
</HTML>	