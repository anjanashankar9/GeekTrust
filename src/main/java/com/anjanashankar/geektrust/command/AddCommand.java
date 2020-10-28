package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Person;

/** Using AddCommand Pattern to add new relations.
 * @Author Anjana Shankar
 * @Created 2020-10-27
 */
public interface AddCommand {
    public void execute(Person p);
}
