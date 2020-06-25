package com.sfgpetclinic.services;

import com.sfgpetclinic.model.Owner;
import com.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findByLastName(String lastName);

    Vet findbyId(Long id);

    Vet save(Vet vet);

    Set<Owner> findAll();

}
