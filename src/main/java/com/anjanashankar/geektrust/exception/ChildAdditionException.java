package com.anjanashankar.geektrust.exception;

import com.anjanashankar.geektrust.Constants;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class ChildAdditionException extends Exception {

    public ChildAdditionException() {
        super(Constants.CHILD_ADDITION_FAILED);
    }
}
