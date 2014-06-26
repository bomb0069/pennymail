describe("Contact", function() {
    it("parse json to attribute table",function(){
    	var html, jsonInput, expectOutput;
    	jsonInput = {"email": "penny@gmail.com", "attributes": [{"key": "Firstname", "value": "Penny"}]};
    	expectOutput = '<table border="1" style="border-collapse: collapse;">' + "\n";
    	expectOutput += '<tr><td id="email" colspan="2">penny@gmail.com</td></tr>' + "\n";
    	expectOutput += '<tr><td>Firstname</td><td>Penny</td></tr>' + "\n";
    	expectOutput += '</table>';
    	html = convertJsonToTable(jsonInput);
    	expect(html).toEqual(expectOutput);
    });
    
    it("set html to contact-attribute", function(){
    	node = document.createElement('td');
    	node.setAttribute('id', 'contact-attributes');
    	node.setAttribute('style', 'display:none');
    	document.getElementsByTagName('html')[0].appendChild(node);
    	jsonInput = {"email": "penny@gmail.com", "attributes": [{"key": "Firstname", "value": "Penny"}]};
    	showAttribute(document, jsonInput);
    	expect(document.getElementById('email').innerHTML).toEqual("penny@gmail.com");
    });
});