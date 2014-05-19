package xpug.kata.birthday_greetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {
		MessageService messageService = new SmtpMessageService("localhost", 25);

        BirthdayService service = new BirthdayService(messageService);

        service.sendGreetings("src/test/resources/employee_data.txt", new XDate("2008/10/08"));
	}

}
