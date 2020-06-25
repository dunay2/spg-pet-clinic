package com.sfgpetclinic.services;

import com.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerService extends CrudRepository {

    Owner findByLastName(String lastName);

    Owner findbyId(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();

}
