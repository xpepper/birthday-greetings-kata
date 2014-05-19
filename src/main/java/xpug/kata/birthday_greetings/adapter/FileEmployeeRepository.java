package xpug.kata.birthday_greetings.adapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import xpug.kata.birthday_greetings.domain.Employee;
import xpug.kata.birthday_greetings.domain.EmployeeRepository;
import xpug.kata.birthday_greetings.domain.XDate;

public class FileEmployeeRepository implements EmployeeRepository {

    private String fileName;

    public FileEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> findEmployeesBornOn(XDate xDate) throws FileNotFoundException, IOException, ParseException {
        List<Employee> employeesToCollect = new ArrayList<Employee>();

        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(fileName));
            String current = "";
            current = buffer.readLine(); // skip header
            while ((current = buffer.readLine()) != null) {
                String[] employeeData = current.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
                if (employee.isBirthday(xDate)) {
                    employeesToCollect.add(employee);
                }
            }
        } finally {
            if (buffer != null)
                buffer.close();

        }
        return employeesToCollect;
    }

}
