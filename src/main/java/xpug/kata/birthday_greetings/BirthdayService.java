package xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {

    private MessageService messageService;

    public BirthdayService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendGreetings(String fileName, XDate xDate) throws IOException, ParseException, AddressException, MessagingException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str = "";
        str = in.readLine(); // skip header
        while ((str = in.readLine()) != null) {
            String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
            if (employee.isBirthday(xDate)) {
                Greetings greetings = new Greetings(employee);
                messageService.send(greetings);
            }
        }
    }

}
