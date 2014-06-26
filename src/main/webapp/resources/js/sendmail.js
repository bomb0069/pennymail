function showMessageBox()
{
	$('#noTopicConfirmOverlay').show();
}
function hideMessageBox()
{
	$("#noTopicConfirmOverlay").hide();
}
function onSubmitForm(topic,callbackfunction)
{
	if(topic=="")
	{
		callbackfunction();
		return false;
	}
	return true;
}

function onCancleButtonClick(callbackfunction)
{
	callbackfunction();
}
function onOkButtonClick(element)
{
	element.submit();
}
