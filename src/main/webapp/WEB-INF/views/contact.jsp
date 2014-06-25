<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>
<html>
<head>
	<title>Admin</title>
</head>
<body>
<form action="/pennymail/addrecipient" method="post">
	<table>
		<tr>
		<td>Recipients</td>
		<td><textarea id="addRecipients" name="addRecipients" rows= "2" cols="50"></textarea></td>
		<td><input type="submit" id="add" value="ADD"></td> 
		<td>
		<c:if test="${invalidList != null}">แสรดด e Penny โง่! invalid email: </c:if>
		<c:forEach items="${invalidList}" var="mail">
				${mail}
			<br />
		</c:forEach>
		</td> 
		</tr>
		<tr>
		<td>Recipients List</td>
		<td colspan = "2">
			<select multiple="multiple" size="10" style="width: 200px;">
			<c:forEach items="${contactList}" var="contactName">
				<option value="${contactName}">${contactName}</option>
			</c:forEach>
			</select>
		</td> 
		<td>
		</td>
		</tr>	
	</table>
</form>
</body>
</html>
