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
public class SisterInLaw implements GetRelationshipCommand {
    Person person;

    public SisterInLaw(Person p) {
        this.person = p;
    }

    @Override
    public String execute() {
        ArrayList<Person> sisterInLaw = new ArrayList<>();

        //Wives of siblings
        if(person.getGender() == Gender.MALE) {
            if(person.getMother() != null) {
                List<Person> relations = person.getMother().getChildren();
                for (Person r : relations) {
                    if (r.getGender() == Gender.MALE && r != person) {
                        Person sp = r.getSpouse();
                        if (sp != null) {
                            sisterInLaw.add(sp);
                        }
                    }
                }
            }
        }
        //Spouse Sisters
        if(person.getSpouse() != null && person.getSpouse().getMother() != null) {
            List<Person> relations = person.getSpouse().getMother().getChildren();
            for (Person r : relations) {
                if (r.getGender() == Gender.FEMALE && r != person) {
                    sisterInLaw.add(r);
                }
            }
        }
        sisterInLaw.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for(Person p: sisterInLaw) {
            sb.append(p.getName()+" ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
