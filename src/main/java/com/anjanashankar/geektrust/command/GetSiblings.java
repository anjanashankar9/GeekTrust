package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Person;
import com.anjanashankar.geektrust.PersonComparator;

import java.util.List;

import static com.anjanashankar.geektrust.Constants.NONE;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class Siblings implements GetRelationshipCommand {
    Person person;

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String execute() {
        List<Person> siblings = person.getMother().getChildren();
        //TODO: USE MAP AND FILTER
        siblings.remove(person);
        siblings.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for(Person p: siblings) {
            sb.append(p.getName()+" ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
