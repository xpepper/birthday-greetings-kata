package xpug.kata.birthday_greetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpMessageService implements MessageService {

    private String smtpHost;
    private int smtpPort;

    public SmtpMessageService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    @Override
    public void send(Greetings greetings) throws AddressException, MessagingException {
        Session session = createMailSession(smtpHost, smtpPort);

        Message message = buildMessage(greetings, session);

        sendMessage(message);
    }

    private Message buildMessage(Greetings greetings, Session session) throws MessagingException, AddressException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("sender@here.com"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(greetings.getRecipient()));
        msg.setSubject(greetings.getSubject());
        msg.setText(greetings.getMessage());
        return msg;
    }

    private Session createMailSession(String host, int port) {
        java.util.Properties properties = new java.util.Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "" + port);
        return Session.getInstance(properties, null);
    }

    // made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }

}
