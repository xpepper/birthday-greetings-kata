package xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xpug.kata.birthday_greetings.adapter.FileEmployeeRepository;
import xpug.kata.birthday_greetings.adapter.SmtpMessageService;
import xpug.kata.birthday_greetings.domain.BirthdayService;
import xpug.kata.birthday_greetings.domain.MessageService;
import xpug.kata.birthday_greetings.domain.XDate;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

public class AcceptanceTest {

    private static final int NONSTANDARD_PORT = 3003;
    private SimpleSmtpServer server;
    private Iterator<SmtpMessage> emailIterator;

    @Before
    public void setUp() throws Exception {
        server = SimpleSmtpServer.start(NONSTANDARD_PORT);
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void sendGreetings() throws Exception {
        startBirthdayServiceFor("src/test/resources/employee_data.txt", "2008/10/08");

        expectNumberOfEmailSentIs(1);
        expectEmailWithSubject_andBody_sentTo("Happy Birthday!", "Happy Birthday, dear John!", "john.doe@foobar.com");
    }

    private void expectEmailWithSubject_andBody_sentTo(String subject, String body, String recipient) {
        SmtpMessage message = emailIterator.next();
        assertEquals(body, message.getBody());
        assertEquals(subject, message.getHeaderValue("Subject"));
        assertEquals(recipient, message.getHeaderValue("To"));
    }

    private void expectNumberOfEmailSentIs(int expected) {
        assertEquals(expected, server.getReceivedEmailSize());
    }

    @SuppressWarnings("unchecked")
    private void startBirthdayServiceFor(String employeeFileName, String date) throws Exception {
        MessageService messageService = new SmtpMessageService("localhost", NONSTANDARD_PORT);
        FileEmployeeRepository employeeRepository = new FileEmployeeRepository(employeeFileName);

        BirthdayService service = new BirthdayService(messageService, employeeRepository);
        service.sendGreetings(new XDate(date));
        emailIterator = server.getReceivedEmail();
    }
}
