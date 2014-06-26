function convertJsonToTable(jsonData) {
	var openTable, email, attributeRows, closeTable;
	openTable = '<table border="1" style="border-collapse: collapse;">'
			+ "\n";
	email = '<tr><td id="email" colspan="2">' + jsonData.email + '</td></tr>' + "\n";
	attributeRows = "";
	for ( var attribute in jsonData.attributes) {
		attributeRows += '<tr><td>' + jsonData.attributes[attribute]["key"] + '</td><td>'
				+ jsonData.attributes[attribute]["value"] + '</td></tr>' + "\n";
	}
	closeTable = '</table>';
	return openTable + email + attributeRows + closeTable;
}

function showAttribute(document, obj) {
	document.getElementById("contact-attributes").innerHTML = convertJsonToTable(obj);
}