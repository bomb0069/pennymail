<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
<title>Admin</title>
<script type="text/javascript"
	src="<c:url value="/resources/js/contact.js" />"></script>
</head>
<body>

	<table>
		<tr>
			<form action="/pennymail/addrecipient" method="post">
			<td>Recipients</td>
			<td><textarea id="addRecipients" name="addRecipients" rows="2"
					cols="50"></textarea></td>
			<td><input type="submit" id="add" value="ADD"></td>
			</form>
			<td><c:if test="${invalidList.size() > 0}">แสรดด e Penny โง่! invalid email: </c:if>
				<c:forEach items="${invalidList}" var="mail">
				${mail}
			<br />
				</c:forEach></td>
		</tr>
		<tr>
			<td>Recipients List</td>
			<td colspan="2"><textarea id="currentRecipientsList" size="10" style="width: 200px;">
<c:forEach items="${contactList}" var="contactName">${contactName}
</c:forEach>
			</textarea>
		</td>
			<td><form method="POST" action="saveAttributes"
					id="contact-attributes"></form></td>
		</tr>
	</table>
</body>
</html>