describe("Contact", function() {
    it("parse json to attribute table",function(){
    	var html, jsonInput, expectOutput;
    	jsonInput = {"email": "penny@gmail.com", "attributes": [{"key": "Firstname", "value": "Penny"}]};
    	expectOutput = '<table border="1" style="border-collapse: collapse;">' + "\n";
    	expectOutput += '<tr><td colspan="2">penny@gmail.com</td></tr>' + "\n";
    	expectOutput += '<tr><td>Firstname</td><td>Penny</td></tr>' + "\n";
    	expectOutput += '</table>';
    	html = convertJsonToTable(jsonInput);
    	expect(html).toEqual(expectOutput);
    });
});