package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.exception.ChildAdditionException;
import com.anjanashankar.geektrust.exception.PersonNotFoundException;
import com.anjanashankar.geektrust.exception.SpouseAdditionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-29
 */
class FamilyTreeTest {
    FamilyTree familyTree;

    void initializeFamilyTree() {
        familyTree = new FamilyTree();
        familyTree.addFamilyHead("Head", "Female");
    }

    @Test
    void testAddFamilyHead() {
        initializeFamilyTree();
        assertEquals("Head", familyTree.getFamilyHead().getName());
        assertEquals(Gender.FEMALE, familyTree.getFamilyHead().getGender());
    }

    @Test
    void testAddFamilyHeadIllegalArgumentGenderException() {
        assertThrows(IllegalArgumentException.class, () -> {
            familyTree = new FamilyTree();
            familyTree.addFamilyHead("Head", "Test");
        });
    }

    @Test
    void testAddSpouse() throws PersonNotFoundException, SpouseAdditionException {
        initializeFamilyTree();
        familyTree.addSpouse("Head", "Spouse", "Male");
        assertEquals("Spouse", familyTree.getFamilyHead().getSpouse().getName());
        assertEquals(Gender.MALE, familyTree.getFamilyHead().getSpouse().getGender());
    }

    @Test
    void testAddSpouseWithPersonNotFoundException() {
        initializeFamilyTree();
        assertThrows(PersonNotFoundException.class, () -> {
            familyTree.addSpouse("Head2", "Spouse", "Male");
        });
    }

    @Test
    void testAddSpouseWithNullMember() {
        initializeFamilyTree();
        assertThrows(PersonNotFoundException.class, () -> {
            familyTree.addSpouse(null, "Spouse", "Male");
        });
    }

    @Test
    void testAddChild() throws ChildAdditionException, PersonNotFoundException {
        initializeFamilyTree();
        familyTree.addChild("Head", "Child1", "Female", false);
        assertEquals(1, familyTree.getFamilyHead().getChildren().size());
        assertEquals("Child1", familyTree.getFamilyHead().getChildren().get(0).getName());
        assertEquals(Gender.FEMALE, familyTree.getFamilyHead().getChildren().get(0).getGender());
    }

    void helperMethodToTestChildAdditionWithSpouse() throws PersonNotFoundException, SpouseAdditionException {
        familyTree = new FamilyTree();
        familyTree.addFamilyHead("Head", "Male");
        familyTree.addSpouse("Head", "Spouse", "Female");
    }

    @Test
    void testAddChildWithSpouseSearch() throws ChildAdditionException, PersonNotFoundException, SpouseAdditionException {
        helperMethodToTestChildAdditionWithSpouse();
        familyTree.addChild("Spouse", "Child1", "Female", false);
        assertEquals(1, familyTree.getFamilyHead().getSpouse().getChildren().size());
        assertEquals("Child1", familyTree.getFamilyHead().getSpouse().getChildren().get(0).getName());
        assertEquals(Gender.FEMALE, familyTree.getFamilyHead().getSpouse().getChildren().get(0).getGender());
    }

    @Test
    void testAddChildWithChildSearch() throws ChildAdditionException, PersonNotFoundException, SpouseAdditionException {
        helperMethodToTestChildAdditionWithSpouse();
        familyTree.addChild("Spouse", "Child1", "Female", false);
        familyTree.addChild("Child1", "Child2", "Male", false);
        assertEquals(1, familyTree.getFamilyHead().getSpouse().getChildren().get(0).getChildren().size());
        assertEquals("Child2", familyTree.getFamilyHead().getSpouse().getChildren().get(0).getChildren().get(0).getName());
        assertEquals(Gender.MALE, familyTree.getFamilyHead().getSpouse().getChildren().get(0).getChildren().get(0).getGender());
    }

    @Test
    void testAddChildTwoLevelSearch() throws ChildAdditionException, PersonNotFoundException {
        initializeFamilyTree();
        familyTree.addChild("Head", "Child2", "Female", false);
        familyTree.addChild("Child2", "Child3", "Female", false);
        familyTree.addChild("Child3", "Child4", "Male", false);
        assertEquals(1, familyTree.getFamilyHead().getChildren().
                get(0).getChildren().get(0).getChildren().size());

    }

    void helperMethodToTestRelationships() throws SpouseAdditionException, PersonNotFoundException, ChildAdditionException {
        familyTree = new FamilyTree();
        familyTree.addFamilyHead("Queen Anga", "Female");
        familyTree.addSpouse("Queen Anga", "King Shan", "Male");
        familyTree.addChild("Queen Anga","Chit","Male", true);
        familyTree.addSpouse("Chit","Amba","Female");
        familyTree.addChild("Queen Anga","Ish","Male",true);
        familyTree.addChild("Queen Anga","Vich","Male", true);
        familyTree.addSpouse("Vich","Lika", "Female");
        familyTree.addChild("Queen Anga","Aras","Male",true);
        familyTree.addSpouse("Aras","Chitra","Female");
        familyTree.addChild("Queen Anga","Satya","Female",true);
        familyTree.addSpouse("Satya","Vyan","Male");
        familyTree.addChild("Amba","Dritha","Female",true);
        familyTree.addChild("Amba","Tritha","Female",true);
        familyTree.addChild("Amba","Vritha","Male",true);
        familyTree.addSpouse("Dritha","Jaya","Male");
        familyTree.addChild("Dritha","Yodhan","Male",true);
        familyTree.addChild("Lika","Vila","Female",true);
        familyTree.addChild("Lika","Chika","Female",true);
        familyTree.addChild("Chitra","Jnki","Female",true);
        familyTree.addSpouse("Jnki","Arit","Male");
        familyTree.addChild("Chitra","Ahit","Male",true);
        familyTree.addChild("Jnki","Laki","Male",true);
        familyTree.addChild("Jnki","Lavnya","Female",true);
    }

    @Test
    void testGetRelationship() throws PersonNotFoundException, SpouseAdditionException, ChildAdditionException {
        helperMethodToTestRelationships();
        assertEquals("Dritha Tritha", familyTree.getRelationship("Chit", Relationships.DAUGHTER.toString()));
        assertEquals("Vritha", familyTree.getRelationship("Chit", Relationships.SON.toString()));
        assertEquals("Ish Vich Aras Satya", familyTree.getRelationship("Chit", Relationships.SIBLINGS.toString()));
        assertEquals("Satya", familyTree.getRelationship("Dritha", Relationships.PATERNAL_AUNT.toString()));
        assertEquals("Ish Vich Aras", familyTree.getRelationship("Dritha", Relationships.PATERNAL_UNCLE.toString()));
        assertEquals("Tritha", familyTree.getRelationship("Yodhan", Relationships.MATERNAL_AUNT.toString()));
        assertEquals("Vritha", familyTree.getRelationship("Yodhan", Relationships.MATERNAL_UNCLE.toString()));
        assertEquals("Chit Ish Aras", familyTree.getRelationship("Lika", Relationships.BROTHER_IN_LAW.toString()));
        assertEquals("Jaya", familyTree.getRelationship("Tritha", Relationships.BROTHER_IN_LAW.toString()));
        assertEquals("Satya", familyTree.getRelationship("Lika", Relationships.SISTER_IN_LAW.toString()));
        assertEquals("Amba Chitra", familyTree.getRelationship("Vich", Relationships.SISTER_IN_LAW.toString()));
    }

    @Test
    void testGetRelationshipWithNoneValues() throws PersonNotFoundException, SpouseAdditionException, ChildAdditionException {
        helperMethodToTestRelationships();
        assertEquals("NONE", familyTree.getRelationship("Ish", Relationships.DAUGHTER.toString()));
        assertEquals("NONE",familyTree.getRelationship("Ish", Relationships.SON.toString()));
        assertEquals("NONE", familyTree.getRelationship("Yodhan", Relationships.SIBLINGS.toString()));
        assertEquals("NONE", familyTree.getRelationship("Yodhan", Relationships.PATERNAL_AUNT.toString()));
        assertEquals("NONE", familyTree.getRelationship("Arit", Relationships.PATERNAL_UNCLE.toString()));
        assertEquals("NONE", familyTree.getRelationship("Dritha", Relationships.MATERNAL_AUNT.toString()));
        assertEquals("NONE", familyTree.getRelationship("Dritha", Relationships.MATERNAL_UNCLE.toString()));
        assertEquals("NONE", familyTree.getRelationship("Dritha", Relationships.BROTHER_IN_LAW.toString()));
        assertEquals("NONE", familyTree.getRelationship("Jnki", Relationships.SISTER_IN_LAW.toString()));
    }

    @Test
    void testGetRelationshipWithIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()-> {
            helperMethodToTestRelationships();
            familyTree.getRelationship("Vich", "RANDOM");
        });

    }

    @Test
    void testGetRelationshipWithPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, ()-> {
            helperMethodToTestRelationships();
            familyTree.getRelationship("Test", Relationships.SISTER_IN_LAW.toString());
        });

    }
}