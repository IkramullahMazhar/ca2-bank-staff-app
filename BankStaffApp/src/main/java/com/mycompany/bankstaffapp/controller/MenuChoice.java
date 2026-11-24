package com.mycompany.bankstaffapp.controller;

/**
 * The options on the main menu.
 * The view loops through values() to print the menu, and the controller
 * switches on these, so the menu numbers aren't hard coded anywhere.
 * To add a new option I only have to add one line here.
 *
 * @author Ikramullah Mazhar
 */
public enum MenuChoice {

    SORT("SORT"),
    SEARCH("SEARCH"),
    ADD_RECORDS("ADD RECORDS"),
    BINARY_TREE("Create a binary tree"),
    EXIT("EXIT");

    private final String label;

    MenuChoice(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    // the number shown on screen - worked out from the position in the enum
    public int getNumber() {
        return ordinal() + 1;
    }

    // turns what the user typed ("1" or "sort") into a menu choice
    // returns null if it doesn't match anything
    public static MenuChoice fromInput(String input) {
        if (input == null) {
            return null;
        }
        String text = input.trim();
        for (MenuChoice choice : values()) {
            if (text.equals(String.valueOf(choice.getNumber())) || text.equalsIgnoreCase(choice.label)) {
                return choice;
            }
        }
        return null;
    }
}