package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.command.*;
import com.anjanashankar.geektrust.exception.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static com.anjanashankar.geektrust.Constants.NONE;
import static com.anjanashankar.geektrust.Constants.PERSON_NOT_FOUND;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-27
 */
public class FamilyTree {
    private Person familyHead;

    public void addFamilyHead(String name, String gender) {
        this.familyHead = new Person(name,
                Gender.fromString(gender),
                null, null);
    }

    public Person searchByName(String name) throws PersonNotFoundException {
        Person p = searchByName(familyHead, name);
        if(p == null) {
            throw new PersonNotFoundException(PERSON_NOT_FOUND);
        }
        return p;
    }

    public Person searchByName(Person p, String name) {
        if(p == null || name == null)
            return  null;

        if(p.getName().equals(name)) {
            return p;
        }
        if(p.getSpouse() != null && p.getSpouse().getName().equals(name)) {
            return p.getSpouse();
        }

        Person found = null;
        List<Person> childlist = new ArrayList<>();
        if (p.getGender() == Gender.FEMALE) {
            childlist = p.getChildren();
        } else if (p.getSpouse() != null) {
            childlist = p.getSpouse().getChildren();
        }

        for(Person c: childlist) {
            found = searchByName(c, name);
            if(found != null) {
                return found;
            }
        }
        return null;
    }

    public void addSpouse(String name, String spouseName, String gender) throws PersonNotFoundException {
        AddSpouse addSpouse = new AddSpouse(searchByName(name));
        Person newPerson = new Person(spouseName, Gender.fromString(gender), null, null);
        addSpouse.execute(newPerson);
    }

    public void addChild(String name, String childName, String gender) throws PersonNotFoundException {
        AddChild addChild = new AddChild(searchByName(name));
        Person newPerson = new Person(childName, Gender.fromString(gender), null, null);
        addChild.execute(newPerson);
    }

    public String getRelationship(String name, String relation) throws PersonNotFoundException {
        Person person = searchByName(name);
        if(person == null) {
            return PERSON_NOT_FOUND;
        }
        Relationships relationship = Relationships.fromString(relation);

        switch (relationship) {
            case DAUGHTER:
                return new Daughter(person).execute();

            case SON:
                return new Son(person).execute();

            case SIBLINGS:
                return new Siblings(person).execute();

            case SISTER_IN_LAW:
                return new SisterInLaw(person).execute();

            case BROTHER_IN_LAW:
                return new BrotherInLaw(person).execute();

            case MATERNAL_AUNT:
                return new MaternalAunt(person).execute();

            case PATERNAL_AUNT:
                return new PaternalAunt(person).execute();

            case MATERNAL_UNCLE:
                return new MaternalUncle(person).execute();

            case PATERNAL_UNCLE:
                return new PaternalUncle(person).execute();

            default:
                return NONE;

        }

    }
}
