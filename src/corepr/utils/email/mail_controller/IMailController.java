package corepr.utils.email.mail_controller;

import corepr.utils.email.exceptions.NoNewEmailException;
import corepr.utils.email.model_letter.Letter;

import javax.mail.Folder;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by macaque on 27.07.2016.
 */
public interface IMailController {

    Folder connectToEmail(String host, String storeType, String user,
                          String password);
    List<Letter> getNewEmails(Folder folder, String location) throws MessagingException, IOException, NoNewEmailException;


}


