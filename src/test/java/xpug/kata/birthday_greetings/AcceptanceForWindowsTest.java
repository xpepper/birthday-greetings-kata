package xpug.kata.birthday_greetings;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcceptanceForWindowsTest {

    private List<Greetings> messagesSent;
    private BirthdayService service;

    @Before
    public void setUp() throws Exception {
        messagesSent = new ArrayList<Greetings>();

        MessageService messageService = new MessageService() {
            @Override
            public void send(Greetings greetings) throws AddressException, MessagingException {
                messagesSent.add(greetings);
            }
        };

        service = new BirthdayService(messageService);
    }

    @Test
    public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

        service.sendGreetings("src/test/resources/employee_data.txt", new XDate("2008/10/08"));

        assertEquals("message not sent?", 1, messagesSent.size());

        Greetings message = messagesSent.get(0);
        assertEquals("Happy Birthday, dear John!", message.getMessage());
        assertEquals("Happy Birthday!", message.getSubject());
        assertEquals("john.doe@foobar.com", message.getRecipient());
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        service.sendGreetings("src/test/resources/employee_data.txt", new XDate("2008/01/01"));

        assertEquals("what? messages?", 0, messagesSent.size());
    }
}
