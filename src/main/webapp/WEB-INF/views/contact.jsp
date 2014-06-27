<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
<title>Admin</title>
<style type="text/css">
body {
	font-family: Arial;
	background-color: #eee;
}

#title {
	color: #fff;
	padding: 10px;
	background: #000;
}

#add {
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border: 1px solid #DBDBDB;
	background: #6A9FD4;
	padding: 6px 20px;
	color: #fff;
	margin: 20px 0 0 20px;
}
#recipientBox {
	padding: 10px;
	border-bottom: 1px solid #ccc;
}
#recipientBox label,#topicBox label {
	float: left;
	width: 120px;
}
#recipientBox label {
	margin-top: 25px;
}
#addRecipients {
	width: 400px;
	float: left;
}
.errorMsg {
	padding-top: 10px;
	clear: both;
	color: red;
}
</style>
</head>
<body>
	<form action="/pennymail/addrecipient" method="post">
		
		<div id="addContactBox" style="margin: 20px auto; border: 1px solid #ccc; width: 700px; background-color: #fff;">	
			<div id="title">Contact List</div>
			<div id="recipientBox">
				<label>Recipients</label>
				<textarea id="addRecipients" name="addRecipients" rows="4" cols="50"></textarea>
				<input type="submit" id="add" value="ADD">
				<div class="errorMsg">
					<c:if test="${invalidList.size() > 0}">แสรดด e Penny โง่! invalid email: </c:if>
					<c:forEach items="${invalidList}" var="mail">
				${mail}<br />
					</c:forEach>
				</div>
			</div>
			
			<div id="recipientListBox" style="padding: 10px; border-bottom: 1px solid #ccc;">
				<label>Recipients List</label>
				<textarea id="currentRecipientsList" rows="20"
						style="width: 100%;">
<c:forEach items="${contactList}" var="contactName">${contactName}
</c:forEach>
			</textarea>
			</div>
		</div>
	</form>
</body>
</html>