package xpug.kata.birthday_greetings;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Before;
import org.junit.Test;

import xpug.kata.birthday_greetings.adapter.FileEmployeeRepository;
import xpug.kata.birthday_greetings.domain.BirthdayService;
import xpug.kata.birthday_greetings.domain.Greetings;
import xpug.kata.birthday_greetings.domain.MessageService;
import xpug.kata.birthday_greetings.domain.XDate;

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

        service = new BirthdayService(messageService, new FileEmployeeRepository("src/test/resources/employee_data.txt"));
    }

    @Test
    public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

        service.sendGreetings(new XDate("2008/10/08"));

        assertEquals("message not sent?", 1, messagesSent.size());

        Greetings message = messagesSent.get(0);
        assertEquals("Happy Birthday, dear John!", message.getMessage());
        assertEquals("Happy Birthday!", message.getSubject());
        assertEquals("john.doe@foobar.com", message.getRecipient());
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        service.sendGreetings(new XDate("2008/01/01"));

        assertEquals("what? messages?", 0, messagesSent.size());
    }
}
