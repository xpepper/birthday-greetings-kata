package xpug.kata.birthday_greetings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeRepository {

    public abstract List<Employee> findEmployeesBornOn(XDate xDate) throws FileNotFoundException, IOException, ParseException;

}