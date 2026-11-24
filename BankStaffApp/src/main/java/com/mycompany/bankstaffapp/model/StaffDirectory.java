package com.mycompany.bankstaffapp.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model - keeps the list of all Harbour Bank staff in memory.
 * The controller asks this class to do things with the data
 * (load, and later sort, search, add and build the tree).
 *
 * @author Ikramullah Mazhar
 */
public class StaffDirectory {

    private List<StaffMember> staff = new ArrayList<>();
    private final StaffFileLoader loader = new StaffFileLoader();

    // loads the file and returns how many staff came in
    public int loadFromFile(String fileName) throws IOException {
        staff = loader.load(fileName);
        return staff.size();
    }

    public int getRejectedLines() {
        return loader.getRejectedLines();
    }

    public int size() {
        return staff.size();
    }

    // gives back a copy so nobody outside can change the real list by accident
    public List<StaffMember> getAll() {
        return new ArrayList<>(staff);
    }
}