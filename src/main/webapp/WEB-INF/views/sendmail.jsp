<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>Penny Mail</title>
</head>
<body>
<form action="/pennymail/sendmail" method="post">
	<label>RECIPIENTS</label><textarea id="recipients" name="recipients">${mail.recipients}</textarea>
	<br>
	<label>TOPIC</label>
	<br>
	<input type="text" id="topic" name="topic" value="${mail.topic}">
	<br>
	<label>BODY</label>
	<br>
	<textarea id="message" name="message">${mail.message}</textarea>
	<br>
	<input id="send" type="submit" value="SEND">
</form>
<c:forEach items="${errorList}" var="errorMessage">
	<span>${errorMessage}</span><br/>
</c:forEach>
</body>
</html>
