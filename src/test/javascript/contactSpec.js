describe("Contact", function() {
    it("parse json to attribute table",function(){
    	var html, jsonInput, expectOutput;
    	jsonInput = {"email": "penny@gmail.com", "attributes": [{"key": "Firstname", "value": "Penny"}]};
    	expectOutput = '<table border="1" style="border-collapse: collapse;">' + "\n";
    	html = convertJsonToTable(jsonInput);
    	expect(html).toContain(expectOutput);
    });
    
    it("set html to contact-attribute", function(){
    	var node = document.createElement('td');
    	node.setAttribute('id', 'contact-attributes');
    	node.setAttribute('style', 'display:none');
    	document.getElementsByTagName('html')[0].appendChild(node);
    	jsonInput = {"email": "penny@gmail.com", "attributes": [{"key": "Firstname", "value": "Penny"}]};
    	showAttribute(document, jsonInput);
    	expect(document.getElementById('email').innerHTML).toContain('penny@gmail.com');
    });
});