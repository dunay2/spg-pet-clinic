package com.sfgpetclinic.services;

import com.sfgpetclinic.model.Owner;
import com.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findByLastName(String lastName);

    Pet findbyId(Long id);

    Pet save(Pet pet);

    Set<Owner> findAll();

}
