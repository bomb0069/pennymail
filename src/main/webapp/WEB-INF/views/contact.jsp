<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Admin</title>
</head>
<body>
<form action="/pennymail/addrecipient" method="post">
	<table>
		<tr>
		<td>Recipients</td>
		<td><textarea id = "addRecipients" rows= "1"></textarea></td>
		<td><input type = "submit" id="add" value = "ADD"></td> 
		<td>
		<c:forEach items="${invalidList}" var="mail">
				${mail}
			<br />
		</c:forEach>
		</td> 
		</tr>
		<tr>
		<td>Recipients List</td>
		<td colspan = "2">
			<textarea readonly id = "currentRecipientsList"  style= "overflow-y: auto; overflow-x: hidden"  rows= "4">
			<c:forEach items="${contactList}" var="contactName">
				${contactName}
			<br />
			</c:forEach>
			</textarea>
		</td> 
		<td>
		</td>
		</tr>	
	</table>
</form>
</body>
</html>
