package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Person;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-27
 */
public class AddChild implements AddCommand {
    Person mother;

    public AddChild(Person mother) {
        this.mother = mother;
    }

    @Override
    public void execute(Person p) {
        mother.addChild(p);
    }
}
