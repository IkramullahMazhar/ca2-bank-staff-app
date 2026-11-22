package com.mycompany.bankstaffapp;

import com.mycompany.bankstaffapp.model.StaffFileLoader;
import com.mycompany.bankstaffapp.model.StaffMember;
import java.io.IOException;
import java.util.List;

/**
 * Harbour Bank staff app - CA2 Algorithms and Constructs
 *
 * @author Ikramullah Mazhar
 */
public class BankStaffApp {

    public static void main(String[] args) {
        StaffFileLoader loader = new StaffFileLoader();

        try {
            // load every line of the file into a list of StaffMember objects
            List<StaffMember> staff = loader.load("Applicants_Form.txt");
            System.out.println("File read successfully - " + staff.size() + " staff loaded, "
                    + loader.getRejectedLines() + " lines rejected");

            // print everyone so we can see the data came in properly
            for (StaffMember member : staff) {
                System.out.println(member);
            }
        } catch (IOException e) {
            // file is missing or in the wrong folder
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }
}