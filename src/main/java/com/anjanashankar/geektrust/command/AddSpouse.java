package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Person;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-27
 */
public class AddSpouse implements AddCommand {
    Person spouse;

    public AddSpouse(Person spouse) {
        this.spouse = spouse;
    }

    @Override
    public void execute(Person p) {
        spouse.addSpouse(p);
    }
}
