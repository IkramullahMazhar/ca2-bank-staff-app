package com.mycompany.bankstaffapp.controller;

import com.mycompany.bankstaffapp.model.StaffDirectory;
import com.mycompany.bankstaffapp.view.BankConsole;
import java.io.IOException;

/**
 * Controller - the middle man between the Model and the View.
 * It takes the user's choice from the view, asks the model to do the
 * work, then tells the view what to show.
 *
 * @author Ikramullah Mazhar
 */
public class StaffController {

    private final StaffDirectory directory; // the model
    private final BankConsole console;      // the view

    public StaffController(StaffDirectory directory, BankConsole console) {
        this.directory = directory;
        this.console = console;
    }

    // starts the program: load the file, then keep showing the menu until EXIT
    public void start() {
        loadStaffFile();

        boolean running = true;
        while (running) {
            console.showMenu();
            MenuChoice choice = MenuChoice.fromInput(console.ask(""));

            if (choice == null) {
                console.showError("Please enter a number from 1 to " + MenuChoice.values().length);
                continue; // show the menu again
            }
            console.show(choice.getLabel() + " selected");

            switch (choice) {
                case SORT:
                case SEARCH:
                case ADD_RECORDS:
                case BINARY_TREE:
                    // each of these gets built in its own commit
                    console.show("Not built yet - coming in the next update.");
                    break;
                case EXIT:
                    console.show("Goodbye from Harbour Bank!");
                    running = false;
                    break;
            }
        }
    }

    // keeps asking for a filename until one opens
    private void loadStaffFile() {
        while (true) {
            String fileName = console.ask("Please enter the filename to read: ").trim();
            try {
                int count = directory.loadFromFile(fileName);
                console.show("File read successfully (" + count + " staff loaded, "
                        + directory.getRejectedLines() + " lines rejected)");
                return;
            } catch (IOException e) {
                console.showError("Could not open '" + fileName + "'. Check the name and try again.");
            }
        }
    }
}