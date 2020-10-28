package com.anjanashankar.geektrust.exception;

import com.anjanashankar.geektrust.Constants;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class CommandNotFoundException extends Exception {

    public CommandNotFoundException() {
        super(Constants.INVALID_COMMAND);
    }
}
