<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Admin</title>
</head>
<body>
<form action="/pennymail/addrecipient" method="post">
	<table>
		<tr>
		<td><textarea id = "recipients" rows= "10"></textarea></td>
		<td><input type = "submit" id="add" value = "ADD"></td> 
		</tr>
	</table>
</form>
</body>
</html>
