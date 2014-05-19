package xpug.kata.birthday_greetings;

import org.junit.Test;

import xpug.kata.birthday_greetings.domain.Employee;
import xpug.kata.birthday_greetings.domain.Greetings;

import static org.junit.Assert.assertEquals;

public class GreetingsTest {

    @Test
    public void buildsAGreetingMessage() throws Exception {
        Employee employee = new Employee("piero", "di bello", "1972/06/26", "pierodibello@gmail.com");
        Greetings greetings = new Greetings(employee );

        assertEquals("Happy Birthday, dear piero!", greetings.getMessage());
    }

}
