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
public class GetSisterInLaws implements GetRelationshipCommand {
    Member member;

    @Override
    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String execute() {
        ArrayList<Member> sisterInLaw = new ArrayList<>();

        //Wives of siblings
        if (member.getMother() != null) {
            List<Member> relations = member.getMother().getChildren();
            for (Member r : relations) {
                if (r.getGender() == Gender.MALE && r != member) {
                    Member sp = r.getSpouse();
                    if (sp != null) {
                        sisterInLaw.add(sp);
                    }
                }
            }
        }
        //Spouse Sisters
        if (member.getSpouse() != null && member.getSpouse().getMother() != null) {
            List<Member> relations = member.getSpouse().getMother().getChildren();
            for (Member r : relations) {
                if (r.getGender() == Gender.FEMALE && r != member) {
                    sisterInLaw.add(r);
                }
            }
        }
        sisterInLaw.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for (Member p : sisterInLaw) {
            sb.append(p.getName() + " ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
