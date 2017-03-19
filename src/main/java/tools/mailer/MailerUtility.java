package tools.mailer;

import common.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by abe20538 on 27/02/2017.
 */
public class MailerUtility {

    private static final Logger LOG = LoggerFactory.getLogger(MailerUtility.class);

    public static boolean sendMailThroughOutlook(final String subject, final String recipient, final String htmlContent) {
        final Properties props = PropertyLoader.loadPropertiesFileByName("mail");
        final String username = props.getProperty("user.username");
        final String password = props.getProperty("user.password");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(htmlContent, "text/html");

            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            LOG.error(e.getMessage(), e);
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
