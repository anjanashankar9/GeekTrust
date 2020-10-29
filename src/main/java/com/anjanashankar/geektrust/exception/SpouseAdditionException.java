package com.anjanashankar.geektrust.exception;

import com.anjanashankar.geektrust.Constants;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class SpouseAdditionException extends Exception {

    public SpouseAdditionException() {
        super(Constants.SPOUSE_ADDITION_FAILED);
    }
}
