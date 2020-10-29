package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.command.*;
import com.anjanashankar.geektrust.exception.ChildAdditionException;
import com.anjanashankar.geektrust.exception.PersonNotFoundException;
import com.anjanashankar.geektrust.exception.SpouseAdditionException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class FamilyTree {

    private Member familyHead;

    public Member getFamilyHead() {
        return familyHead;
    }

    public void addFamilyHead(String name, String gender) {
        this.familyHead = new Member(name,
                Gender.fromString(gender),
                null, null);
    }

    private Member searchByName(String name) throws PersonNotFoundException {
        Member member = searchByName(familyHead, name);
        if (member == null) {
            throw new PersonNotFoundException();
        }
        return member;
    }

    private Member searchByName(Member member, String name) {
        if (member == null || name == null)
            return null;

        if (member.getName().equals(name)) {
            return member;
        }
        if (member.getSpouse() != null && member.getSpouse().getName().equals(name)) {
            return member.getSpouse();
        }

        Member found = null;
        List<Member> childlist = new ArrayList<>();
        if (member.getGender() == Gender.FEMALE) {
            childlist = member.getChildren();
        } else if (member.getSpouse() != null) {
            childlist = member.getSpouse().getChildren();
        }

        for (Member c : childlist) {
            found = searchByName(c, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public void addSpouse(String name, String spouseName, String gender) throws PersonNotFoundException, SpouseAdditionException {
        Member member = searchByName(name);
        Member newMember = new Member(spouseName, Gender.fromString(gender), null, null);
        member.addSpouse(newMember);
    }

    public void addChild(String name, String childName, String gender) throws PersonNotFoundException, ChildAdditionException {
        Member member = searchByName(name);
        Member newMember = new Member(childName, Gender.fromString(gender), null, null);
        member.addChild(newMember);
        System.out.println(Constants.CHILD_ADDITION_SUCCEEDED);
    }

    public String getRelationship(String name, String relation) throws PersonNotFoundException {
        Member member = searchByName(name);
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

        }
        cmd.setMember(member);
        return cmd.execute();
    }
}
