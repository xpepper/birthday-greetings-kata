package xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MessageService {

    public abstract void send(Greetings greetings) throws AddressException, MessagingException;

}