package com.mycompany.bankstaffapp.model;

/**
 * The "Manager Type" from the brief. These are the only roles that
 * exist in Harbour Bank, so nobody can be given a role that isn't here.
 *
 * The rank number is the role's level in the bank.
 * 1 is the top (Branch Manager) and 6 is the bottom (Clerk).
 * I'll use it later when building the hierarchy tree.
 *
 * @author Ikramullah Mazhar
 */
public enum BankRole {

    BRANCH_MANAGER("Branch Manager", 1),
    DEPUTY_MANAGER("Deputy Manager", 2),
    DEPARTMENT_HEAD("Department Head", 3),
    TEAM_LEADER("Team Leader", 4),
    OFFICER("Officer", 5),
    CLERK("Clerk", 6);

    private final String displayName; // how the role is written in the file and on screen
    private final int rank;           // 1 = most senior

    BankRole(String displayName, int rank) {
        this.displayName = displayName;
        this.rank = rank;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getRank() {
        return rank;
    }

    // true for the roles that manage other people (Team Leader and above)
    public boolean isManager() {
        return rank <= TEAM_LEADER.rank;
    }

    // finds the role from text like "team leader" (case doesn't matter)
    // gives back null if the role doesn't exist, so the caller can show an error
    public static BankRole fromText(String text) {
        if (text == null) {
            return null;
        }
        for (BankRole role : values()) {
            if (role.displayName.equalsIgnoreCase(text.trim())) {
                return role;
            }
        }
        return null;
    }

    // finds the role from its number in the on-screen list (1 = Branch Manager)
    public static BankRole fromNumber(int number) {
        if (number < 1 || number > values().length) {
            return null; // number is outside the list
        }
        return values()[number - 1];
    }

    @Override
    public String toString() {
        return displayName;
    }
}