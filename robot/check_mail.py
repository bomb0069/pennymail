import imaplib
import base64

class check_mail:
 
    ROBOT_LIBRARY_SCOPE = 'GLOBAL'
    __version__ = '0.1'
 
    def open_mail_box(self):
       try:
            self.mailbox = imaplib.IMAP4_SSL('imap.gmail.com',993)
            self.mailbox.login('penny.inspectorgadget@gmail.com','csd2014xitgmLwmp')
            self.mailbox.select()
            
       except imaplib.IMAP4.error:
            raise AssertionError('Could not connect to the mailbox')
    
    def check_mail(self, *args):
        r, self.item = self.mailbox.search(None, *args)
        self.item = self.item[0].split()
        if self.item == []:
            raise AssertionError("No mail found")       

    def should_receive_new_mail(self, topic, expect):
        response, item = self.mailbox.search(None, '(UNSEEN FROM "juacompe.ig@gmail.com" SUBJECT "%s")' % topic)
        actual = len(item[0].split())
        
        for mail_id in item[0].split():
            self.mailbox.store(mail_id, '+FLAGS', '\\Seen')

        if int(actual) != int(expect):
            raise AssertionError('Expect mail count %s but was %s' % (expect, actual))     
 
    def read_all_mail(self):
        response, item = self.mailbox.search(None, '(UNSEEN FROM "juacompe.ig@gmail.com")')
        
        for mail_id in item[0].split():
            self.mailbox.store(mail_id, '+FLAGS', '\\Seen')
        
    def get_mail_body_by_mail_topic(self, topic):
    	response, item = self.mailbox.search(None, '(UNSEEN FROM "juacompe.ig@gmail.com" SUBJECT "%s")' % topic)
    	mail_ids = item[0].split()
    	
    	if len(mail_ids) == 0:
    	    raise AssertionError("No mail with topic %s found" % topic)
    	
        body = self.mailbox.fetch(mail_ids[0], '(BODY[TEXT])')[1][0][1]
        body = base64.b64decode(body).decode('UTF-8')
        
        return body
 
    def close_mailbox(self):
        self.mailbox.close() 