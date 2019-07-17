package nl.ordina.recruitmentexperience.enums;

/**
 * This enum represents the Departments used within the applications
 */
public enum Department {
    OSD("OSD"),
    TNS("T&S");

    private String department;

    public String getDepartment() {
        return department;
    }

    Department(String department){
        this.department = department;
    }
}
