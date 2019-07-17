package nl.ordina.recruitmentexperience.enums;

/**
 * This enum represents the Region used within the applications
 */
public enum Region {

    NOORD("Noord"),
    MIDDEN("Midden"),
    ZUID("Zuid");

    private String region;

    public String getRegion() {
        return region;
    }

    Region(String region){
        this.region = region;
    }
}
