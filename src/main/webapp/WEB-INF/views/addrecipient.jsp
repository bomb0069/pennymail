<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Admin</title>
</head>
<body>
<form action="/pennymail/addrecipient" method="post">
	<table>
		<tr>
		<td><textarea id = "recipients" rows= "1"></textarea></td>
		<td><input type = "submit" id="add" value = "ADD"></td> 
		<td>
		<c:forEach items="${duplicateList}" var="mail">
				${mail.duplicateNameList}
			<br />
		</c:forEach>
		</td> 
		</tr>
		<tr>
		<td colspan = "2">
			<textarea id = "recipients"  style= "overflow-y: auto;"  rows= "4">
			</textarea>
		</td> 
		</tr>
		
	</table>
</form>
</body>
</html>
