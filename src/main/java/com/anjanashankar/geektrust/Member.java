package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.exception.ChildAdditionException;
import com.anjanashankar.geektrust.exception.SpouseAdditionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that defines a family member.
 *
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class Member {
    private static int count = 0;
    private int sequenceId;
    private String name;
    private Gender gender;

    private Member mother;
    private Member father;

    private List<Member> children;
    private Member spouse;

    public Member(String name, Gender gender, Member mother, Member father) {
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

    public Gender getGender() {
        return gender;
    }

    public Member getMother() {
        return mother;
    }

    public void setMother(Member mother) {
        this.mother = mother;
    }

    public Member getFather() {
        return father;
    }

    public void setFather(Member father) {
        this.father = father;
    }

    public List<Member> getChildren() {
        return children;
    }

    public Member getSpouse() {
        return spouse;
    }

    public void addSpouse(Member spouse) throws SpouseAdditionException {
        if (this.spouse == null) {
            this.spouse = spouse;
            spouse.spouse = this;
        } else {
            throw new SpouseAdditionException();
        }
    }

    public void addChild(Member member) throws ChildAdditionException {
        if (this.gender == Gender.FEMALE) {
            this.children.add(member);
            member.setMother(this);
            member.setFather(this.spouse);
        } else {
            throw new ChildAdditionException();
        }
    }
}
