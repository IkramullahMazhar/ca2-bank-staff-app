package com.mycompany.bankstaffapp.model;

/**
 * One member of staff at Harbour Bank.
 * This class only holds the data - it never prints anything itself.
 * (That's the Model part of MVC.)
 *
 * @author Ikramullah Mazhar
 */
public class StaffMember {

    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String email;
    private final double salary;
    private final BankDepartment department;
    private final BankRole role;          // the "Manager Type" from the brief
    private final String jobTitle;
    private final String bank;

    // full constructor - used when a line is read from the file
    public StaffMember(String firstName, String lastName, String gender, String email, double salary,
            BankDepartment department, BankRole role, String jobTitle, String bank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.role = role;
        this.jobTitle = jobTitle;
        this.bank = bank;
    }

    // short constructor - used later when the user adds someone new
    // (the brief only asks for name, manager type and department)
    public StaffMember(String firstName, String lastName, BankRole role, BankDepartment department) {
        this(firstName, lastName, "", "", 0, department, role, "", "Harbour Bank");
    }

    // sorting and searching will both use the full name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public BankDepartment getDepartment() {
        return department;
    }

    public BankRole getRole() {
        return role;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getBank() {
        return bank;
    }

    // e.g. "Kate Summer | Team Leader | IT Services"
    @Override
    public String toString() {
        return getFullName() + " | " + role + " | " + department;
    }
}