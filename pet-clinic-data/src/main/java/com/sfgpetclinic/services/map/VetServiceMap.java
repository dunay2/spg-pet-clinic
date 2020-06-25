package com.sfgpetclinic.services.map;

import com.sfgpetclinic.model.Vet;
import com.sfgpetclinic.services.VetService;

import java.util.Set;


public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(Vet object) {
        super.delete(object);

    }

    @Override
    public Set<Vet> findAll() {
        return null;
    }

    @Override
    public Vet findById(Long id) {
        return null;
    }

    @Override
    public Vet save(Vet object) {
        return null;
    }

    @Override
    public Vet findByLastName(String lastName) {
        return null;
    }
}
