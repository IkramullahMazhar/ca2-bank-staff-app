package com.mycompany.bankstaffapp;

import com.mycompany.bankstaffapp.controller.StaffController;
import com.mycompany.bankstaffapp.model.StaffDirectory;
import com.mycompany.bankstaffapp.view.BankConsole;

/**
 * Harbour Bank staff app - CA2 Algorithms and Constructs
 * Main just creates the Model, View and Controller and starts the program.
 *
 * @author Ikramullah Mazhar
 */
public class BankStaffApp {

    public static void main(String[] args) {
        StaffDirectory model = new StaffDirectory();   // Model - the data
        BankConsole view = new BankConsole();           // View - the screen
        StaffController controller = new StaffController(model, view); // Controller - the logic
        controller.start();
    }
}