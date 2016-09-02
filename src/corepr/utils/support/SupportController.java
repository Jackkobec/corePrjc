package corepr.utils.support;

import corepr.utils.PropertiesHolder;
import corepr.utils.email.exceptions.NoNewEmailException;
import corepr.utils.email.mail_controller.MailController;
import corepr.utils.email.model_letter.Letter;
import corepr.utils.email.smtp.SMTP;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class SupportController implements ISupportController {
    private Map<String, String> properties;
    private static final String HOST = "supporthost";
    private static final String MAIL_STORE_TYPE = "supportmailStoreType";
    private static final String USER_NAME = "supportusername";
    private static final String PASSWORD = "supportpassword";
    private static final String LOCATION = "supportlocation";

    @Override
    public void sendEmail(String email, String textMessage) {
        try {
            SMTP.sendMailFromSupport(email, textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Letter> getNewEmails(){
        MailController mailController = new MailController();
        List<Letter> letterList = new ArrayList<>();

        try {
            letterList = mailController.getNewEmails(
                                                    mailController.connectToEmail(
                                                                                PropertiesHolder.get(HOST),
                                                                                PropertiesHolder.get(MAIL_STORE_TYPE),
                                                                                PropertiesHolder.get(USER_NAME),
                                                                                PropertiesHolder.get(PASSWORD)),
                                                                                                                PropertiesHolder.get(LOCATION));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoNewEmailException e) {
            //e.printStackTrace();
        }

        return letterList;
    }
}
