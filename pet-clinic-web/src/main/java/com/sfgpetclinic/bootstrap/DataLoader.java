package com.sfgpetclinic.bootstrap;

import com.sfgpetclinic.model.Owner;
import com.sfgpetclinic.model.Vet;
import com.sfgpetclinic.services.OwnerService;
import com.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michale");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Princess");

        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Sagaz");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Bilbo");
        vet2.setLastName("Bolson");

        vetService.save(vet2);

        System.out.println("Loaded Vets");


    }


}
