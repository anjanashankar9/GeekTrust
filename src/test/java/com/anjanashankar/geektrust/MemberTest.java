package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.exception.ChildAdditionException;
import com.anjanashankar.geektrust.exception.SpouseAdditionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-29
 */
class MemberTest {
    Member member;

    @Test
    void testGetSequenceId() {
        member = new Member("TestName", Gender.FEMALE, null, null);
        Member newMember = new Member("Test2", Gender.MALE, null, null);
        assertTrue(newMember.getSequenceId() > member.getSequenceId());
    }

    @Test
    void testAddSpouse() throws SpouseAdditionException {
        member = new Member("TestName", Gender.FEMALE, null, null);
        Member newMember = new Member("Test2", Gender.MALE, null, null);
        member.addSpouse(newMember);
        assertEquals(newMember, member.getSpouse());
    }

    @Test
    void testAddSpouseWithSpouseAdditionException() throws SpouseAdditionException {
        member = new Member("TestName", Gender.FEMALE, null, null);
        Member newMember = new Member("Test2", Gender.MALE, null, null);
        member.addSpouse(newMember);

        assertThrows(SpouseAdditionException.class, () -> {
            Member m = new Member("Test3", Gender.MALE, null, null);
            member.addSpouse(m);
        });
    }

    @Test
    void testAddChild() throws ChildAdditionException {
        member = new Member("TestName", Gender.FEMALE, null, null);
        Member child = new Member("Test2", Gender.MALE, null, null);
        member.addChild(child);
        assertEquals(1, member.getChildren().size());
        assertEquals(child, member.getChildren().get(0));
    }

    @Test
    void testAddChildWithChildAdditionException() {
        member = new Member("TestName", Gender.FEMALE, null, null);
        Member newMember = new Member("Test2", Gender.MALE, null, null);
        Member child = new Member("Test2", Gender.MALE, null, null);
        assertThrows(ChildAdditionException.class, () -> {
            newMember.addChild(child);
        });
    }
}