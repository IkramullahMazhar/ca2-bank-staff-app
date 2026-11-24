package com.mycompany.bankstaffapp.view;

import com.mycompany.bankstaffapp.controller.MenuChoice;
import com.mycompany.bankstaffapp.model.StaffMember;
import java.util.List;
import java.util.Scanner;

/**
 * View - everything the user sees or types goes through this class.
 * No other class uses System.out or Scanner.
 *
 * @author Ikramullah Mazhar
 */
public class BankConsole {

    // column widths for the staff table: number, name, manager type, department
    private static final String TABLE_ROW = "%-4s %-22s %-17s %-22s%n";

    private final Scanner input = new Scanner(System.in);

    // shows a question and returns what the user typed
    public String ask(String question) {
        System.out.print(question);
        if (!input.hasNextLine()) {
            return ""; // no more input (stops a crash if the input closes)
        }
        return input.nextLine();
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(">> " + message);
    }

    public void showHeading(String heading) {
        System.out.println();
        System.out.println("--- " + heading + " ---");
    }

    // prints the main menu by looping through the enum
    public void showMenu() {
        System.out.println();
        System.out.println("Do You wish to SORT or SEARCH:");
        for (MenuChoice choice : MenuChoice.values()) {
            System.out.println(choice.getNumber() + ". " + choice.getLabel());
        }
    }

    // prints staff as a neat table
    public void showStaffTable(List<StaffMember> list) {
        System.out.printf(TABLE_ROW, "No.", "Name", "Manager Type", "Department");
        System.out.println("-".repeat(68));
        int row = 1;
        for (StaffMember member : list) {
            System.out.printf(TABLE_ROW, row + ".", member.getFullName(), member.getRole(), member.getDepartment());
            row++;
        }
    }
}