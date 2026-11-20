package com.mycompany.bankstaffapp.model;

/**
 * The departments in Harbour Bank. A new staff member can only be put
 * into one of these, which keeps the data valid.
 *
 * Each department also has a short 3-letter code (like a desk sign)
 * so the user can type "LOA" instead of "Loans and Mortgages".
 *
 * @author Ikramullah Mazhar
 */
public enum BankDepartment {

    RETAIL_BANKING("Retail Banking", "RET"),
    LOANS_AND_MORTGAGES("Loans and Mortgages", "LOA"),
    CUSTOMER_SERVICE("Customer Service", "CUS"),
    FINANCE_AND_ACCOUNTS("Finance and Accounts", "FIN"),
    RISK_AND_COMPLIANCE("Risk and Compliance", "RSK"),
    IT_SERVICES("IT Services", "ITS"),
    HUMAN_RESOURCES("Human Resources", "HRM");

    private final String displayName;
    private final String code;

    BankDepartment(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCode() {
        return code;
    }

    // accepts the full name ("IT Services") or the code ("ITS"), any case
    // returns null if it doesn't match a real department
    public static BankDepartment fromText(String text) {
        if (text == null) {
            return null;
        }
        String cleaned = text.trim();
        for (BankDepartment dept : values()) {
            if (dept.displayName.equalsIgnoreCase(cleaned) || dept.code.equalsIgnoreCase(cleaned)) {
                return dept;
            }
        }
        return null;
    }

    // finds the department from its number in the on-screen list
    public static BankDepartment fromNumber(int number) {
        if (number < 1 || number > values().length) {
            return null;
        }
        return values()[number - 1];
    }

    @Override
    public String toString() {
        return displayName;
    }
}