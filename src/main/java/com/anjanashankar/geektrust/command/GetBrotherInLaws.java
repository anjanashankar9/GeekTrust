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
public class GetBrotherInLaws implements GetRelationshipCommand {
    Member member;

    @Override
    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String execute() {
        ArrayList<Member> brotherInLaw = new ArrayList<>();

        //Husband of siblings
        if (member.getGender() == Gender.FEMALE) {
            if (member.getMother() != null) {
                List<Member> relations = member.getMother().getChildren();
                for (Member r : relations) {
                    if (r.getGender() == Gender.FEMALE && r != member) {
                        Member sp = r.getSpouse();
                        if (sp != null) {
                            brotherInLaw.add(sp);
                        }
                    }
                }
            }
        }
        //Spouse Brothers
        if (member.getSpouse() != null && member.getSpouse().getMother() != null) {
            List<Member> relations = member.getSpouse().getMother().getChildren();
            for (Member r : relations) {
                if (r.getGender() == Gender.MALE && r != member.getSpouse()) {
                    brotherInLaw.add(r);
                }
            }
        }
        brotherInLaw.sort(new PersonComparator());
        StringBuilder sb = new StringBuilder();
        for (Member p : brotherInLaw) {
            sb.append(p.getName() + " ");
        }
        return sb.length() == 0 ? NONE : sb.toString().trim();
    }
}
