function showMessageBox()
{
	return "";
}

function onSubmitForm(topic,callbackfunc)
{
	if(topic=="")
	{
		callbackfunction();
		return false;
	}
	return true;
}
