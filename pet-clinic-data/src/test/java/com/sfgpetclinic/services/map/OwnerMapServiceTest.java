package com.sfgpetclinic.services.map;

import com.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class OwnerMapServiceTest {

    private final Long ownerId = 1L;
    private final Long otherOwnerId = 2L;
    private final String lastName = "Smith";
    private OwnerMapService ownerMapService;
    @Mock
    private PetTypeMapService petTypeMapService;
    @Mock
    private PetMapService petMapService;


    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(petTypeMapService, petMapService);
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @DisplayName("Return all owners")
    @Test
    void findAll() {
        Set<Owner> Owners = ownerMapService.findAll();

        assertEquals(1, Owners.size());
    }

    @DisplayName("Delete owner by id")
    @Test
    void deleteById() {

        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());

    }

    @DisplayName("Delete owner")
    @Test
    void delete() {

        Owner owner = ownerMapService.findById(ownerId);

        ownerMapService.delete(owner);

        assertEquals(0, ownerMapService.findAll().size());

    }

    @DisplayName("Save owner with id")
    @Test
    void saveWithId() {
        Owner owner = Owner.builder().id(otherOwnerId).build();

        Owner savedOwner = ownerMapService.save(owner);

        assertEquals(2, ownerMapService.findAll().size());
        assertEquals(otherOwnerId, savedOwner.getId());
    }

    @DisplayName("Save owner without id")
    @Test
    void saveNoId() {

        Owner savedOwner = ownerMapService.save(new Owner());

        assertEquals(2, ownerMapService.findAll().size());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());


    }


    @DisplayName("Find owner by id")
    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(1, owner.getId());
    }

    @DisplayName("Find by last name")
    @Test
    void findByLastName() {

        Owner owner = ownerMapService.findByLastName(lastName);

        assertEquals(1, owner.getId());

    }

    @DisplayName("Searchs for a name that does not exists")
    @Test
    void findByLastNameNotFound() {

        Owner owner = ownerMapService.findByLastName(lastName + "no");
        assertNull(owner);
    }
}