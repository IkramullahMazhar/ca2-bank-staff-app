package com.mycompany.bankstaffapp.model;

import com.mycompany.bankstaffapp.algorithm.StaffMergeSort;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model - keeps the list of all Harbour Bank staff in memory.
 * The controller asks this class to do things with the data
 * (load, sort, and later search, add and build the tree).
 *
 * @author Ikramullah Mazhar
 */
public class StaffDirectory {

    private List<StaffMember> staff = new ArrayList<>();
    private final StaffFileLoader loader = new StaffFileLoader();
    private final StaffMergeSort sorter = new StaffMergeSort();
    private boolean sortedByName = false; // binary search will need this to be true

    // loads the file and returns how many staff came in
    public int loadFromFile(String fileName) throws IOException {
        staff = loader.load(fileName);
        sortedByName = false; // the file is in random order
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

    // sorts everyone A-Z with the recursive merge sort
    public void sortByName() {
        sorter.sort(staff, StaffMergeSort.BY_NAME);
        sortedByName = true;
    }

    public boolean isSortedByName() {
        return sortedByName;
    }

    // how many comparisons the last sort needed
    public int getLastSortComparisons() {
        return sorter.getComparisons();
    }

    // the first n people in the list (or fewer if the list is shorter)
    public List<StaffMember> getFirst(int n) {
        int end = Math.min(n, staff.size());
        return new ArrayList<>(staff.subList(0, end));
    }
}