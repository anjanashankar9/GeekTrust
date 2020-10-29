package com.anjanashankar.geektrust.command;

import com.anjanashankar.geektrust.Member;

/**
 * Using the command pattern to implement the get relationships.
 *
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public interface GetRelationshipCommand {
    void setMember(Member member);

    String execute();
}
