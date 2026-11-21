package com.mycompany.bankstaffapp;

import com.mycompany.bankstaffapp.model.BankDepartment;
import com.mycompany.bankstaffapp.model.BankRole;

/**
 * Harbour Bank staff app - CA2 Algorithms and Constructs
 *
 * @author Ikramullah Mazhar
 */
public class BankStaffApp {

    public static void main(String[] args) {
        // quick check that both enums work - this gets replaced by the menu later
        System.out.println("Harbour Bank roles (rank order):");
        for (BankRole role : BankRole.values()) {
            System.out.println("  " + role.getRank() + ". " + role);
        }

        System.out.println("Harbour Bank departments:");
        for (BankDepartment dept : BankDepartment.values()) {
            System.out.println("  [" + dept.getCode() + "] " + dept);
        }

        // test the lookups, including one that should fail
        System.out.println("fromText(\"team leader\") -> " + BankRole.fromText("team leader"));
        System.out.println("fromText(\"loa\") -> " + BankDepartment.fromText("loa"));
        System.out.println("fromText(\"Pizza\") -> " + BankDepartment.fromText("Pizza"));
    }
}