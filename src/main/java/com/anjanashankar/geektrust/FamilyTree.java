package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.command.AddChild;
import com.anjanashankar.geektrust.command.AddSpouse;

import java.util.ArrayList;
import java.util.List;

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

    public Person searchByName(String name) {
        return searchByName(familyHead, name);
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

    public void addSpouse(String name, String spouseName, String gender) {
        AddSpouse addSpouse = new AddSpouse(searchByName(name));
        Person newPerson = new Person(spouseName, Gender.fromString(gender), null, null);
        addSpouse.execute(newPerson);
    }

    public void addChild(String name, String childName, String gender) {
        AddChild addChild = new AddChild(searchByName(name));
        Person newPerson = new Person(childName, Gender.fromString(gender), null, null);
        addChild.execute(newPerson);
    }


}
