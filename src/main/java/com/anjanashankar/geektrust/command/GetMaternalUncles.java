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
public class GetMaternalUncles implements GetRelationshipCommand {
    Member member;

    @Override
    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String execute() {
        ArrayList<Member> maternalUncle = new ArrayList<>();

        if (member.getMother() != null && member.getMother().getMother() != null) {
            List<Member> relations = member.getMother().getMother().getChildren();
            for (Member r : relations) {
                if (r.getGender() == Gender.MALE) {
                    maternalUncle.add(r);
                }
            }
        }

        maternalUncle.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for (Member p : maternalUncle) {
            sb.append(p.getName() + " ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
