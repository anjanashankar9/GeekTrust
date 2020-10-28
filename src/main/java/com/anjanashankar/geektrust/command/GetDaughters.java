package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Gender;
import com.anjanashankar.geektrust.Person;
import com.anjanashankar.geektrust.PersonComparator;

import java.util.ArrayList;
import java.util.List;

import static com.anjanashankar.geektrust.Constants.NONE;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class GetDaughters implements GetRelationshipCommand {
    Person person;

    public GetDaughters() {
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public String execute() {
        if (person.getGender() != Gender.FEMALE) {
            person = person.getSpouse();
        }
        List<Person> children = person.getChildren();
        ArrayList<Person> relations = new ArrayList<>();
        //TODO: USE MAP AND FILTER
        for (Person c : children) {
            if (c.getGender() == Gender.FEMALE) {
                relations.add(c);
            }
        }
        relations.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for (Person p : relations) {
            sb.append(p.getName() + " ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
