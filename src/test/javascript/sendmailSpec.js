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
    it("Close popup when choose 'อุ้ยผิด'", function(){
    	isCalled = false;
    	callbackfunction = function(){
    		isCalled = true;
    	};
    	onCancleButtonClick(callbackfunction);
    	expect(isCalled).toEqual(true);
    });
    it("Send email when choose 'OK เออกูโง่'",function(){
    	isCalled = false;
    	elem = {
    		submit: function() {
    			isCalled = true;
    		}	
    	};
    	onOkButtonClick(elem);
    	expect(isCalled).toEqual(true);
    });
});