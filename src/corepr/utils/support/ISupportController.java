package corepr.utils.support;

import corepr.utils.email.model_letter.Letter;

import java.io.IOException;
import java.util.List;


public interface ISupportController {
    public void sendEmail(String email, String testMessage);
    public List<Letter> getNewEmails() throws IOException;
}
