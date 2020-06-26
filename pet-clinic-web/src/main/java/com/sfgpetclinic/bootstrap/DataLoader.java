package com.sfgpetclinic.bootstrap;

import com.sfgpetclinic.model.Owner;
import com.sfgpetclinic.model.PetType;
import com.sfgpetclinic.model.Vet;
import com.sfgpetclinic.services.OwnerService;
import com.sfgpetclinic.services.VetService;
import com.sfgpetclinic.services.map.PetTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michale");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Princess");

        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Sagaz");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bilbo");
        vet2.setLastName("Bolson");

        vetService.save(vet2);

        System.out.println("Loaded Vets");


    }


}
