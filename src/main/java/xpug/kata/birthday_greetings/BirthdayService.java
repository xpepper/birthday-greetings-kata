package xpug.kata.birthday_greetings;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {

    private MessageService messageService;
    private EmployeeRepository employeeRepository;

    public BirthdayService(MessageService messageService, EmployeeRepository employeeRepository) {
        this.messageService = messageService;
        this.employeeRepository = employeeRepository;
    }

    public void sendGreetings(XDate xDate) throws IOException, ParseException, AddressException, MessagingException {
        List<Employee> employeesToGreet = employeeRepository.findEmployeesBornOn(xDate);

        for (Employee employee : employeesToGreet) {
            Greetings greetings = new Greetings(employee);
            messageService.send(greetings);
        }
    }

}
