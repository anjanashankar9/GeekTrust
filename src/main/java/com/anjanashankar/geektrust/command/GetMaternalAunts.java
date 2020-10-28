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
public class MaternalAunt implements GetRelationshipCommand {
    Person person;

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String execute() {
        ArrayList<Person> maternalAunt = new ArrayList<>();

        if(person.getMother() != null && person.getMother().getMother() != null) {
            List<Person> relations = person.getMother().getMother().getChildren();
            for (Person r : relations) {
                if (r.getGender() == Gender.FEMALE && r != person.getMother()) {
                    maternalAunt.add(r);
                }
            }
        }

        maternalAunt.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for(Person p: maternalAunt) {
            sb.append(p.getName()+" ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
