package com.anjanashankar.geektrust;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public enum Relationships {

    PATERNAL_UNCLE("Paternal-Uncle"),
    MATERNAL_UNCLE("Maternal-Uncle"),
    PATERNAL_AUNT("Paternal-Aunt"),
    MATERNAL_AUNT("Maternal-Aunt"),
    SISTER_IN_LAW("Sister-In-Law"),
    BROTHER_IN_LAW("Brother-In-Law"),
    SON("Son"),
    DAUGHTER("Daughter"),
    SIBLINGS("Siblings");

    private final String string;

    Relationships(String r) {
        this.string = r;
    }

    public static Relationships fromString(String r) {
        for (Relationships relationship : Relationships.values()) {
            if (relationship.string.equalsIgnoreCase(r)) {
                return relationship;
            }
        }
        throw new IllegalArgumentException("I can't understand this Relationship !!!");
    }

    public String toString() {
        return this.string;
    }
}
