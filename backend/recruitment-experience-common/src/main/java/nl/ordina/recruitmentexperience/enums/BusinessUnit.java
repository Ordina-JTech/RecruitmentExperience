package nl.ordina.recruitmentexperience.enums;

/**
 * This enum represents the BusinessUnit used within the applications
 */

public enum BusinessUnit {

    JTECH("JTech"),
    MTECH("MTech"),
    JROOTS("JSRoots"),
    PYTHONEERS("Pythoneers");

    private String businessUnit;

    public String getBusinessUnit() {
        return businessUnit;
    }

    BusinessUnit(String businessUnit){
        this.businessUnit = businessUnit;
    }
}
