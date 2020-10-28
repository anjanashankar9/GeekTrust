package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Person;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-27
 */
public interface Command {
    public void execute(Person p);
}
