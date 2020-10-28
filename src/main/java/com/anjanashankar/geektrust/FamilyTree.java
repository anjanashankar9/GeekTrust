package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.command.*;
import com.anjanashankar.geektrust.exception.ChildAdditionException;
import com.anjanashankar.geektrust.exception.PersonNotFoundException;
import com.anjanashankar.geektrust.exception.RelationshipNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static com.anjanashankar.geektrust.Constants.CHILD_ADDITION_SUCCEEDED;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
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
        if (p == null) {
            throw new PersonNotFoundException();
        }
        return p;
    }

    public Person searchByName(Person p, String name) {
        if (p == null || name == null)
            return null;

        if (p.getName().equals(name)) {
            return p;
        }
        if (p.getSpouse() != null && p.getSpouse().getName().equals(name)) {
            return p.getSpouse();
        }

        Person found = null;
        List<Person> childlist = new ArrayList<>();
        if (p.getGender() == Gender.FEMALE) {
            childlist = p.getChildren();
        } else if (p.getSpouse() != null) {
            childlist = p.getSpouse().getChildren();
        }

        for (Person c : childlist) {
            found = searchByName(c, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public void addSpouse(String name, String spouseName, String gender) throws PersonNotFoundException {
        Person p = searchByName(name);
        Person newPerson = new Person(spouseName, Gender.fromString(gender), null, null);
        p.addSpouse(newPerson);
    }

    public void addChild(String name, String childName, String gender) throws PersonNotFoundException, ChildAdditionException {
        Person p = searchByName(name);
        Person newPerson = new Person(childName, Gender.fromString(gender), null, null);
        p.addChild(newPerson);
        System.out.println(CHILD_ADDITION_SUCCEEDED);
    }

    public String getRelationship(String name, String relation) throws PersonNotFoundException, RelationshipNotFoundException {
        Person person = searchByName(name);
        if (person == null) {
            throw new PersonNotFoundException();
        }
        Relationships relationship = Relationships.fromString(relation);

        GetRelationshipCommand cmd = null;
        switch (relationship) {
            case DAUGHTER:
                cmd = new GetDaughters();
                break;

            case SON:
                cmd = new GetSons();
                break;

            case SIBLINGS:
                cmd = new GetSiblings();
                break;

            case SISTER_IN_LAW:
                cmd = new GetSisterInLaws();
                break;

            case BROTHER_IN_LAW:
                cmd = new GetBrotherInLaws();
                break;

            case MATERNAL_AUNT:
                cmd = new GetMaternalAunts();
                break;

            case PATERNAL_AUNT:
                cmd = new GetPaternalAunts();
                break;

            case MATERNAL_UNCLE:
                cmd = new GetMaternalUncles();
                break;

            case PATERNAL_UNCLE:
                cmd = new GetPaternalUncles();
                break;

            default:
                throw new RelationshipNotFoundException();

        }
        cmd.setPerson(person);
        return cmd.execute();
    }
}
