package xpug.kata.birthday_greetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

import xpug.kata.birthday_greetings.adapter.FileEmployeeRepository;
import xpug.kata.birthday_greetings.adapter.SmtpMessageService;
import xpug.kata.birthday_greetings.domain.BirthdayService;
import xpug.kata.birthday_greetings.domain.EmployeeRepository;
import xpug.kata.birthday_greetings.domain.MessageService;
import xpug.kata.birthday_greetings.domain.XDate;

public class Main {

	public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {
		MessageService messageService = new SmtpMessageService("localhost", 25);
		EmployeeRepository employeeRepository = new FileEmployeeRepository("src/test/resources/employee_data.txt");

        BirthdayService service = new BirthdayService(messageService, employeeRepository);

        service.sendGreetings(new XDate("2008/10/08"));
	}

}
