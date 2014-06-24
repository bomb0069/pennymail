<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Penny Mail</title>
</head>
<body>
<form action="/pennymail/sendmail" method="post">
	<label>RECIPIENTS</label><textarea id="recipients"></textarea>
	<br>
	<label>TOPIC</label>
	<br>
	<input type="text" id="topic">
	<br>
	<label>BODY</label>
	<br>
	<textarea id="message"></textarea>
	<br>
	<input id="send" type="submit" value="SEND">
</form>
</body>
</html>
