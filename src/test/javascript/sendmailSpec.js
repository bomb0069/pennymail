describe("Send Mail", function() {
    it("Given the topic is empty and it click send, then the error message box should show ",function(){
    	topic = "";
    	isCalled = false;
    	callbackfunction = function(){
    		isCalled = true;
    	};
    	expect(onSubmitForm(topic,callbackfunction)).toEqual(false);
    	expect(isCalled).toEqual(true);
    	
    });
});