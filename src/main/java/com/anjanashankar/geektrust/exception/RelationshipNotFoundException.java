package com.anjanashankar.geektrust.exception;

import com.anjanashankar.geektrust.Constants;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class RelationshipNotFoundException extends Exception {

    public RelationshipNotFoundException() {
        super(Constants.PROVIDE_VALID_RELATION);
    }
}
