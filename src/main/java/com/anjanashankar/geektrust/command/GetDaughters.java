package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Gender;
import com.anjanashankar.geektrust.Member;
import com.anjanashankar.geektrust.PersonComparator;

import java.util.ArrayList;
import java.util.List;

import static com.anjanashankar.geektrust.Constants.NONE;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class GetDaughters implements GetRelationshipCommand {
    Member member;

    public GetDaughters() {
    }

    public void setMember(Member member) {
        this.member = member;
    }


    @Override
    public String execute() {
        if (member.getGender() != Gender.FEMALE) {
            member = member.getSpouse();
        }
        List<Member> children = member.getChildren();
        ArrayList<Member> relations = new ArrayList<>();
        //TODO: USE MAP AND FILTER
        for (Member c : children) {
            if (c.getGender() == Gender.FEMALE) {
                relations.add(c);
            }
        }
        relations.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for (Member p : relations) {
            sb.append(p.getName() + " ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
