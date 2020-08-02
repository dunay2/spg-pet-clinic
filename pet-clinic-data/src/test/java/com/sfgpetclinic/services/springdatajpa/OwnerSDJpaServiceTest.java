package com.sfgpetclinic.services.springdatajpa;

import com.sfgpetclinic.model.Owner;
import com.sfgpetclinic.repositories.OwnerRepository;
import com.sfgpetclinic.repositories.PetRepository;
import com.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    private static final String LAST_NAME = "Smith";
    private static final Long OWNER_ID = 1L;
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;
    private Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @DisplayName("Find Owner by last name")
    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(returnedOwner);

        Owner owner = ownerSDJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, owner.getLastName());
    }

    @DisplayName("Get all records from database")
    @Test
    void findAll() {
        //given
        Set<Owner> owners = new HashSet<>();

        owners.add(returnedOwner);
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> ownersCheck = ownerSDJpaService.findAll();

        //then
        assertEquals(2, ownersCheck.size());
    }

    @DisplayName("Find owner by id")
    @Test
    void findById() {
        when(ownerRepository.findById(OWNER_ID)).thenReturn(Optional.of(returnedOwner));

        Owner owner = ownerSDJpaService.findById(OWNER_ID);

        assertEquals(LAST_NAME, owner.getLastName());
    }

    @DisplayName("Find owner by an id not found")
    @Test
    void findByIdNotFound() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = ownerSDJpaService.findById(-1L);

        assertNull(owner);
    }

    @DisplayName("Save owner")
    @Test
    void save() {

        when(ownerRepository.save(returnedOwner)).thenReturn(returnedOwner);

        Owner savedOwner = ownerSDJpaService.save(returnedOwner);

        assertEquals(LAST_NAME, savedOwner.getLastName());

        verify(ownerRepository, times(1)).save(any());
    }

    @DisplayName("Delete owner")
    @Test
    void delete() {
        ownerSDJpaService.delete(returnedOwner);

        verify(ownerRepository, times(1)).delete(any());

    }

    @DisplayName("Delete owner by id")
    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(OWNER_ID);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}