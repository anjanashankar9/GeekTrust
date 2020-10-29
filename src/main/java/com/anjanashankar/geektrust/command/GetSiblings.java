package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Member;
import com.anjanashankar.geektrust.PersonComparator;

import java.util.List;

import static com.anjanashankar.geektrust.Constants.NONE;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class GetSiblings implements GetRelationshipCommand {
    Member member;

    @Override
    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String execute() {
        List<Member> siblings = member.getMother().getChildren();
        siblings.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for (Member p : siblings) {
            if (p != member) {
                sb.append(p.getName() + " ");
            }
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
