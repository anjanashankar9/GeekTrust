package com.anjanashankar.geektrust;

import com.anjanashankar.geektrust.exception.ChildAdditionException;
import com.anjanashankar.geektrust.exception.PersonNotFoundException;
import com.anjanashankar.geektrust.exception.SpouseAdditionException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(familyTree.getFamilyHead().getName(), "Head");
        assertEquals(familyTree.getFamilyHead().getGender(), Gender.FEMALE);
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
        assertEquals(familyTree.getFamilyHead().getSpouse().getName(), "Spouse");
        assertEquals(familyTree.getFamilyHead().getSpouse().getGender(), Gender.MALE);
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
        familyTree.addChild("Head", "Child1", "Female");
        assertEquals(familyTree.getFamilyHead().getChildren().size(), 1);
        assertEquals(familyTree.getFamilyHead().getChildren().get(0).getName(), "Child1");
        assertEquals(familyTree.getFamilyHead().getChildren().get(0).getGender(), Gender.FEMALE);
    }

    void helperMethodToTestChildAdditionWithSpouse() throws PersonNotFoundException, SpouseAdditionException {
        familyTree = new FamilyTree();
        familyTree.addFamilyHead("Head", "Male");
        familyTree.addSpouse("Head", "Spouse", "Female");
    }

    @Test
    void testAddChildWithSpouseSearch() throws ChildAdditionException, PersonNotFoundException, SpouseAdditionException {
        helperMethodToTestChildAdditionWithSpouse();
        familyTree.addChild("Spouse", "Child1", "Female");
        assertEquals(familyTree.getFamilyHead().getSpouse().getChildren().size(), 1);
        assertEquals(familyTree.getFamilyHead().getSpouse().getChildren().get(0).getName(), "Child1");
        assertEquals(familyTree.getFamilyHead().getSpouse().getChildren().get(0).getGender(), Gender.FEMALE);
    }

    @Test
    void testAddChildWithChildSearch() throws ChildAdditionException, PersonNotFoundException, SpouseAdditionException {
        helperMethodToTestChildAdditionWithSpouse();
        familyTree.addChild("Spouse", "Child1", "Female");
        familyTree.addChild("Child1", "Child2", "Male");
        assertEquals(familyTree.getFamilyHead().getSpouse().getChildren().get(0).getChildren().size(), 1);
        assertEquals(familyTree.getFamilyHead().getSpouse().getChildren().get(0).getChildren().get(0).getName(), "Child2");
        assertEquals(familyTree.getFamilyHead().getSpouse().getChildren().get(0).getChildren().get(0).getGender(), Gender.MALE);
    }

    @Test
    void testAddChildTwoLevelSearch() throws ChildAdditionException, PersonNotFoundException {
        initializeFamilyTree();
        familyTree.addChild("Head", "Child2", "Female");
        familyTree.addChild("Child2", "Child3", "Female");
        familyTree.addChild("Child3", "Child4", "Male");
        assertEquals(familyTree.getFamilyHead().getChildren().
                get(0).getChildren().get(0).getChildren().size(), 1);

    }

    void helperMethodToTestRelationships() throws SpouseAdditionException, PersonNotFoundException, ChildAdditionException {
        familyTree = new FamilyTree();
        familyTree.addFamilyHead("Queen Anga", "Female");
        familyTree.addSpouse("Queen Anga", "King Shan", "Male");
        familyTree.addChild("Queen Anga","Chit","Male");
        familyTree.addSpouse("Chit","Amba","Female");
        familyTree.addChild("Queen Anga","Ish","Male");
        familyTree.addChild("Queen Anga","Vich","Male");
        familyTree.addSpouse("Vich","Lika", "Female");
        familyTree.addChild("Queen Anga","Aras","Male");
        familyTree.addSpouse("Aras","Chitra","Female");
        familyTree.addChild("Queen Anga","Satya","Female");
        familyTree.addSpouse("Satya","Vyan","Male");
        familyTree.addChild("Amba","Dritha","Female");
        familyTree.addChild("Amba","Tritha","Female");
        familyTree.addChild("Amba","Vritha","Male");
        familyTree.addSpouse("Dritha","Jaya","Male");
        familyTree.addChild("Dritha","Yodhan","Male");
        familyTree.addChild("Lika","Vila","Female");
        familyTree.addChild("Lika","Chika","Female");
        familyTree.addChild("Chitra","Jnki","Female");
        familyTree.addSpouse("Jnki","Arit","Male");
        familyTree.addChild("Chitra","Ahit","Male");
        familyTree.addChild("Jnki","Laki","Male");
        familyTree.addChild("Jnki","Lavnya","Female");
    }

    @Test
    void testGetRelationship() throws PersonNotFoundException, SpouseAdditionException, ChildAdditionException {
        helperMethodToTestRelationships();
        assertEquals(familyTree.getRelationship("Chit", Relationships.DAUGHTER.toString()), "Dritha Tritha");
        assertEquals(familyTree.getRelationship("Chit", Relationships.SON.toString()), "Vritha");
        assertEquals(familyTree.getRelationship("Chit", Relationships.SIBLINGS.toString()), "Ish Vich Aras Satya");
        assertEquals(familyTree.getRelationship("Dritha", Relationships.PATERNAL_AUNT.toString()), "Satya");
        assertEquals(familyTree.getRelationship("Dritha", Relationships.PATERNAL_UNCLE.toString()), "Ish Vich Aras");
        assertEquals(familyTree.getRelationship("Yodhan", Relationships.MATERNAL_AUNT.toString()), "Tritha");
        assertEquals(familyTree.getRelationship("Yodhan", Relationships.MATERNAL_UNCLE.toString()), "Vritha");
        assertEquals(familyTree.getRelationship("Lika", Relationships.BROTHER_IN_LAW.toString()), "Chit Ish Aras");
        assertEquals(familyTree.getRelationship("Tritha", Relationships.BROTHER_IN_LAW.toString()), "Jaya");
        assertEquals(familyTree.getRelationship("Lika", Relationships.SISTER_IN_LAW.toString()), "Satya");
        assertEquals(familyTree.getRelationship("Vich", Relationships.SISTER_IN_LAW.toString()), "Amba Chitra");
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