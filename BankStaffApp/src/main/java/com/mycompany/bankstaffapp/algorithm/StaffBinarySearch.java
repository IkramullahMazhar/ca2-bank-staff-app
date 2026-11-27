package com.mycompany.bankstaffapp.algorithm;

import com.mycompany.bankstaffapp.model.StaffMember;
import java.util.List;

/**
 * Recursive binary search - finds a person by full name in the
 * list AFTER it has been sorted A-Z by the merge sort.
 *
 * WHY I CHOSE BINARY SEARCH:
 * - The list is already sorted by name, so binary search can use that.
 *   Each check throws away half of the list, so it is O(log n):
 *   with 46 staff it needs at most 6 checks.
 * - Linear search checks people one by one (O(n)) and ignores the fact
 *   that the list is sorted, so the sorting work would be wasted.
 * - A HashMap would also be fast, but it is not recursive, and I would
 *   have to keep a second copy of the data in sync with the list.
 * - Interpolation search is made for evenly spread numbers, not names.
 *
 * @author Ikramullah Mazhar
 */
public class StaffBinarySearch {

    private int steps; // how many names were checked on the last search

    // returns the position of the name in the list, or -1 if it isn't there
    public int findIndex(List<StaffMember> sortedList, String fullName) {
        steps = 0;
        return search(sortedList, fullName, 0, sortedList.size() - 1);
    }

    public int getSteps() {
        return steps;
    }

    // the recursive part: look at the middle, then search only the half
    // where the name has to be
    private int search(List<StaffMember> list, String target, int low, int high) {
        if (low > high) {
            return -1; // base case - nothing left to check, so the name isn't there
        }

        steps++;
        int middle = low + (high - low) / 2; // same as (low+high)/2 but can't overflow
        int result = list.get(middle).getFullName().compareToIgnoreCase(target);

        if (result == 0) {
            return middle;                                  // found it
        } else if (result > 0) {
            return search(list, target, low, middle - 1);   // name comes earlier - go left
        } else {
            return search(list, target, middle + 1, high);  // name comes later - go right
        }
    }
}