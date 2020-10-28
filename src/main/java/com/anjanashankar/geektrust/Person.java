package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.command.GetRelationshipCommand;
import com.anjanashankar.geektrust.exception.ChildAdditionException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class Person {
    private static int count = 0;
    private int sequenceId;
    private String name;
    private Gender gender;

    private Person mother;
    private Person father;

    private List<Person> children;
    private Person spouse;

    public Person(String name, Gender gender, Person mother, Person father) {
        this.sequenceId = ++count;
        this.name = name;
        this.gender = gender;
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();
        this.spouse = null;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void addSpouse(Person spouse) {
        this.spouse = spouse;
        spouse.spouse = this;
    }

    public void addChild(Person person) throws ChildAdditionException {
        if (this.gender == Gender.FEMALE) {
            this.children.add(person);
            person.setMother(this);
            person.setFather(this.spouse);
        } else {
            throw new ChildAdditionException();
        }
    }

    public String getRelationship(GetRelationshipCommand cmd) {

        return cmd.execute();
    }
}
