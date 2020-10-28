package com.anjanashankar.geektrust;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-27
 */
public enum Gender {
    FEMALE("Female"),
    MALE("Male");

    private final String string;

    Gender(String g){
        this.string = g;
    }

    public String toString() {
        return this.string;
    }

    public static Gender fromString(String gender) {
        for (Gender g : Gender.values()) {
            if (g.string.equalsIgnoreCase(gender)) {
                return g;
            }
        }
        throw new IllegalArgumentException("I can't understand the gender !!!");
    }
}
