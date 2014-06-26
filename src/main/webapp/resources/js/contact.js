function convertJsonToTable(jsonData) {
	var openTable, email, attributeRows, closeTable;
	var openTable = '<table border="1" style="border-collapse: collapse;">'
			+ "\n";
	email = '<tr><td colspan="2">' + jsonData.email + '</td></tr>' + "\n";
	var attributes = jsonData.attributes;
	for ( var attribute in attributes) {
		attributeRows = '<tr><td>' + attributes[attribute]["key"] + '</td><td>'
				+ attributes[attribute]["value"] + '</td></tr>' + "\n";
	}
	closeTable = '</table>';
	return openTable + email + attributeRows + closeTable;
}