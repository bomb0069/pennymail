function showMessageBox()
{
	$('#noTopicConfirmOverlay').show();
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
