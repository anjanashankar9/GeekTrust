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
public class GetBrotherInLaws implements GetRelationshipCommand {
    Person person;

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String execute() {
        ArrayList<Person> brotherInLaw = new ArrayList<>();

        //Husband of siblings
        if (person.getGender() == Gender.FEMALE) {
            if (person.getMother() != null) {
                List<Person> relations = person.getMother().getChildren();
                for (Person r : relations) {
                    if (r.getGender() == Gender.FEMALE && r != person) {
                        Person sp = r.getSpouse();
                        if (sp != null) {
                            brotherInLaw.add(sp);
                        }
                    }
                }
            }
        }
        //Spouse Brothers
        if (person.getSpouse() != null && person.getSpouse().getMother() != null) {
            List<Person> relations = person.getSpouse().getMother().getChildren();
            for (Person r : relations) {
                if (r.getGender() == Gender.MALE && r != person) {
                    brotherInLaw.add(r);
                }
            }
        }
        brotherInLaw.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for (Person p : brotherInLaw) {
            sb.append(p.getName() + " ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
