package xpug.kata.birthday_greetings.domain;

public class Greetings {

    private Employee employee;

    public Greetings(Employee employee) {
        this.employee = employee;
    }

    public String getRecipient() {
        return employee.getEmail();
    }

    public String getMessage() {
        String template = "Happy Birthday, dear %NAME%!";
        return template.replace("%NAME%", employee.getFirstName());
    }

    public String getSubject() {
        return "Happy Birthday!";
    }

}
