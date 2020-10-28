package com.anjanashankar.geektrust.exception;

import com.anjanashankar.geektrust.Constants;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException() {
        super(Constants.PERSON_NOT_FOUND);
    }
}
