<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Penny Mail</title>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/sendmail.js" />"></script>
<style type="text/css">
body {
	font-family: Arial;
	background-color: #eee;
}

#noTopicConfirmOverlay {
	position: relative;
	top:-500px;
	border: 1px solid #000;
	padding: 10px;
	width: 300px;
	height: 200px;
	background-color: #fff;
	display: none;
	margin: 0 auto;
}

#okBtn,#cancelBtn {
	bottom: 10px;
}

#okBtn {
	left: 50px;
}

#cancelBtn {
	right: 50px;
}

#recipientsBox {
	padding: 10px;
	border-bottom: 1px solid #ccc;
}

#title {
	color: #fff;
	padding: 10px;
	background: #000;
}

#recipientsBox,#topicBox,#messageBox {
	padding: 10px;
	border-bottom: 1px solid #ccc;
}

#recipientsBox label,#topicBox label {
	float: left;
	width: 120px;
}

#recipientsBox label {
	margin-top: 60px;
}

#topicBox label {
	margin-top: 5px;
}

#recipients,#topic {
	width: 400px;
	float: left;
}

#buttonPanel {
	padding: 10px;
	text-align: center;
}

#composeBox {
	margin: 20px auto;
	border: 1px solid #ccc;
	width: 700px;
	background-color: #fff;
}

.btn {
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border: 1px solid #DBDBDB;
	background: #6A9FD4;
	padding: 6px 20px;
	color: #fff;
}



span.errorMessage{
	color: red;
	margin-left: 120px;
}
</style>
</head>
<body>
	<div id="composeBox">
		<form id="sendMailForm" action="/pennymail/sendmail" method="post"
			onsubmit="return onSubmitForm(document.getElementById('topic').value, showMessageBox)">
			<div id="title">Send Mail</div>
			<div id="recipientsBox">
				<label>RECIPIENTS</label>
				<textarea id="recipients" name="recipients" rows="10">${mail.recipients}</textarea>
				<div style="clear: both;"></div>
				<c:forEach items="${errorList}" var="errorMessage">
					<span class="errorMessage">${errorMessage}</span>
					<br />
				</c:forEach>
			</div>

			<div id="topicBox">
				<label>TOPIC</label> <input type="text" id="topic" name="topic"
					value="${mail.topic}">
				<div style="clear: both;"></div>
			</div>

			<div id="messageBox">
				<label>BODY</label>
				<textarea id="message" name="message" style="width: 100%;" rows="20">${mail.message}</textarea>
			</div>

			<div id="buttonPanel">
				<input id="send" class="btn" type="submit" value="SEND">
			</div>
		</form>
	</div>
	<div id="noTopicConfirmOverlay">
		<h1>e Penny โง่ เดี๋ยวโดนแบนหรอก!</h1>
		<button id="okBtn" class="btn"
			onClick="onOkButtonClick(document.getElementById('sendMailForm'))">OK
			เออกูโง่</button>
		<button id="cancelBtn" class="btn" onClick="onCancleButtonClick(hideMessageBox);">อุ้ยผิด</button>
	</div>

</body>
</html>
