package com.mycompany.bankstaffapp.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the staff file and turns each line into a StaffMember.
 *
 * File columns (comma separated):
 * 0 First name, 1 Last name, 2 Gender, 3 Email, 4 Salary,
 * 5 Department, 6 Manager Type, 7 Job title, 8 Bank
 *
 * Any line with a department or role that doesn't exist is rejected,
 * so bad data never gets into the program.
 *
 * @author Ikramullah Mazhar
 */
public class StaffFileLoader {

    private static final int COLUMNS = 9;

    private int rejectedLines = 0; // how many lines were thrown out on the last load

    public List<StaffMember> load(String fileName) throws IOException {
        // read the whole file in one go - each line becomes one String
        List<String> lines = Files.readAllLines(Path.of(fileName));
        List<StaffMember> staff = new ArrayList<>();
        rejectedLines = 0;

        // start at 1 because line 0 is the header row
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue; // skip blank lines at the end of the file
            }

            StaffMember member = parseLine(line);
            if (member == null) {
                rejectedLines++;
            } else {
                staff.add(member);
            }
        }
        return staff;
    }

    public int getRejectedLines() {
        return rejectedLines;
    }

    // turns one line of text into a StaffMember, or null if the line is bad
    private StaffMember parseLine(String line) {
        String[] cols = line.split(",");
        if (cols.length != COLUMNS) {
            return null; // wrong number of columns
        }

        // the enums do the validation - unknown values come back as null
        BankDepartment department = BankDepartment.fromText(cols[5]);
        BankRole role = BankRole.fromText(cols[6]);
        if (department == null || role == null) {
            return null;
        }

        double salary;
        try {
            salary = Double.parseDouble(cols[4].trim());
        } catch (NumberFormatException e) {
            return null; // salary isn't a number
        }

        return new StaffMember(cols[0].trim(), cols[1].trim(), cols[2].trim(), cols[3].trim(),
                salary, department, role, cols[7].trim(), cols[8].trim());
    }
}