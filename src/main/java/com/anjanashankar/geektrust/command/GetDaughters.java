package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Gender;
import com.anjanashankar.geektrust.Member;
import com.anjanashankar.geektrust.PersonComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            if (member.getSpouse() != null) {
                member = member.getSpouse();
            } else {
                return NONE;
            }
        }
        List<Member> children = member.getChildren();
        List<Member> relations = children.stream()
                .filter(c -> c.getGender() == Gender.FEMALE)
                .collect(Collectors.toList());

        relations.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for (Member p : relations) {
            sb.append(p.getName() + " ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
