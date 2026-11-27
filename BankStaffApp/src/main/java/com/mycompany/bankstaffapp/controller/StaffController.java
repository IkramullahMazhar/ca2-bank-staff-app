package com.mycompany.bankstaffapp.controller;

import com.mycompany.bankstaffapp.model.StaffDirectory;
import com.mycompany.bankstaffapp.model.StaffMember;
import com.mycompany.bankstaffapp.view.BankConsole;
import java.io.IOException;
import java.util.List;

/**
 * Controller - the middle man between the Model and the View.
 * It takes the user's choice from the view, asks the model to do the
 * work, then tells the view what to show.
 *
 * @author Ikramullah Mazhar
 */
public class StaffController {

    private static final int NAMES_TO_SHOW = 20; // the brief asks for the first 20

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
                    sortStaff();
                    break;
                case SEARCH:
                    searchStaff();
                    break;
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

    // SORT: model sorts the list, view shows the first 20
    private void sortStaff() {
        directory.sortByName();
        console.showHeading("Staff sorted A-Z (first " + NAMES_TO_SHOW + " of " + directory.size() + ")");
        console.showStaffTable(directory.getFirst(NAMES_TO_SHOW));
        console.show("Merge sort used " + directory.getLastSortComparisons() + " comparisons.");
    }

    // SEARCH: ask for a name, model does the binary search, view shows the result
    private void searchStaff() {
        if (!directory.isSortedByName()) {
            console.show("(The list wasn't sorted yet, so it is sorted first - binary search needs that.)");
        }

        String name = console.ask("Enter the full name to search (e.g. James Anderson): ");
        if (name.trim().isEmpty()) {
            console.showError("Name can't be empty.");
            return;
        }

        List<StaffMember> matches = directory.searchByName(name);
        if (matches.isEmpty()) {
            console.showError("No staff member called '" + name.trim() + "' was found.");
            console.show("Tip: type the first AND last name, e.g. Kate Summer");
            return;
        }

        console.showHeading("Search result");
        for (StaffMember member : matches) {
            console.showStaffDetails(member);
        }
        console.show("Binary search found it in " + directory.getLastSearchSteps() + " step(s).");
    }
}