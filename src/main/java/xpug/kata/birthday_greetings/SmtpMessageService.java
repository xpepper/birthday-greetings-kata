package xpug.kata.birthday_greetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpMessageService {

    private String smtpHost;
    private int smtpPort;

    public SmtpMessageService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public void sendGreetings(XDate xDate, Employee employee) throws AddressException, MessagingException {
        Greetings greetings = new Greetings(employee);
        sendMessage("sender@here.com", greetings);
    }

    private void sendMessage(String sender, Greetings greetings) throws AddressException, MessagingException {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(greetings.getRecipient()));
        msg.setSubject(greetings.getSubject());
        msg.setText(greetings.getMessage());

        // Send the message
        sendMessage(msg);
    }

    // made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }

}
