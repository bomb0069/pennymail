import imaplib
 
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
 
    def delete_mail_with_topic(self, topic):
        response, item = self.mailbox.search(None, '(UNSEEN FROM "juacompe.ig@gmail.com" SUBJECT "%s")' % topic)
        
        for mail_id in item[0].split():
            self.mailbox.store(mail_id, '+FLAGS', '\\Deleted')
            
        self.mailbox.expunge()
 
    def close_mailbox(self):
        self.mailbox.close() 