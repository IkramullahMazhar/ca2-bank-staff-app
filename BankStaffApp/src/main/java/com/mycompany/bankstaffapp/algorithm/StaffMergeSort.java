package com.mycompany.bankstaffapp.algorithm;

import com.mycompany.bankstaffapp.model.StaffMember;
import java.util.Comparator;
import java.util.List;

/**
 * Recursive merge sort for the staff list.
 *
 * WHY I CHOSE MERGE SORT:
 * - It is always O(n log n), even in the worst case. Quick sort can drop
 *   to O(n^2) when the list is already sorted, and in my program the list
 *   is re-sorted after every new record is added, so it is often almost
 *   sorted already.
 * - It is stable: two people that compare equal keep their order. I need
 *   this later for the hierarchy tree (sort by name first, then by rank,
 *   and people with the same rank stay in A-Z order).
 * - Bubble, selection and insertion sort are O(n^2), so they get slow as
 *   the bank grows. Heap sort is O(n log n) but not stable.
 * - The cost is an extra temp array the same size as the list, which is
 *   fine for a list of staff.
 *
 * @author Ikramullah Mazhar
 */
public class StaffMergeSort {

    // A-Z by full name, ignoring capital letters ("kate" and "Kate" are equal)
    public static final Comparator<StaffMember> BY_NAME
            = (a, b) -> a.getFullName().compareToIgnoreCase(b.getFullName());

    private int comparisons; // counts the work done, shown to the user after sorting

    // sorts the list in place using the given order
    public void sort(List<StaffMember> list, Comparator<StaffMember> order) {
        comparisons = 0;
        if (list.size() < 2) {
            return; // nothing to sort
        }

        // work on an array (fast index access), then copy the result back
        StaffMember[] items = list.toArray(new StaffMember[0]);
        StaffMember[] temp = new StaffMember[items.length];
        mergeSort(items, temp, 0, items.length - 1, order);

        for (int i = 0; i < items.length; i++) {
            list.set(i, items[i]);
        }
    }

    public int getComparisons() {
        return comparisons;
    }

    // the recursive part: split the range in half, sort each half, then merge them
    private void mergeSort(StaffMember[] items, StaffMember[] temp, int left, int right,
            Comparator<StaffMember> order) {
        if (left >= right) {
            return; // base case - one item (or none) is already sorted
        }

        int middle = (left + right) / 2;
        mergeSort(items, temp, left, middle, order);       // sort the left half
        mergeSort(items, temp, middle + 1, right, order);  // sort the right half
        merge(items, temp, left, middle, right, order);    // join the two sorted halves
    }

    // joins two sorted halves (left..middle and middle+1..right) into one sorted range
    private void merge(StaffMember[] items, StaffMember[] temp, int left, int middle, int right,
            Comparator<StaffMember> order) {
        int i = left;        // next item in the left half
        int j = middle + 1;  // next item in the right half
        int k = left;        // next free spot in temp

        // take the smaller front item each time
        while (i <= middle && j <= right) {
            comparisons++;
            // <= takes from the left half when equal - this is what makes it stable
            if (order.compare(items[i], items[j]) <= 0) {
                temp[k++] = items[i++];
            } else {
                temp[k++] = items[j++];
            }
        }

        // one half is finished - copy whatever is left in the other half
        while (i <= middle) {
            temp[k++] = items[i++];
        }
        while (j <= right) {
            temp[k++] = items[j++];
        }

        // copy the merged range back into the real array
        for (k = left; k <= right; k++) {
            items[k] = temp[k];
        }
    }
}