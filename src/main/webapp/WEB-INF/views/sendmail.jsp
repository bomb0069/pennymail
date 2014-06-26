<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>Penny Mail</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/sendmail.js" />"></script>
<style type="text/css">
#noTopicConfirmOverlay {
	position: absolute;
	border: 1px solid #000;
	padding: 10px;
	width: 300px;
	height: 200px;
	background-color: #fff;
	left: 100px;
	top: 50px;
	display: none;
}
#okBtn, #cancelBtn {
	position: absolute;
	bottom: 10px;
}
#okBtn {
	left: 50px;
}
#cancelBtn {
	right: 50px;
}
</style>
</head>
<body>
<form id="sendMailForm" action="/pennymail/sendmail" method="post" onsubmit="return onSubmitForm(document.getElementById('topic').value, showMessageBox)">
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

	<div id="noTopicConfirmOverlay">
		<h1>e Penny โง่ เดี๋ยวโดนแบนหรอก!</h1>
		<button id="okBtn" onClick="onOkButtonClick(document.getElementById('sendMailForm'))">OK เออกูโง่</button>
		<button id="cancelBtn" onClick="onCancleButtonClick(hideMessageBox);">อุ้ยผิด</button>
	</div>
</body>
</html>
