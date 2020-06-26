package com.sfgpetclinic.bootstrap;

import com.sfgpetclinic.model.*;
import com.sfgpetclinic.services.OwnerService;
import com.sfgpetclinic.services.PetTypeService;
import com.sfgpetclinic.services.SpecialtyService;
import com.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("radiology");
        Speciality savedRadoilogy = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michale");
        owner1.setLastName("Weston");
        owner1.setAddress("dirección owner");
        owner1.setCity("Ciudad owner");
        owner1.setTelephone("1231231231231");

        Pet pet = new Pet();
        pet.setPetType(savedDogType);
        pet.setOwner(owner1);
        pet.setBirthDate(LocalDate.now());
        pet.setName("Copito");
        owner1.getPets().add(pet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Princess");
        owner1.setAddress("dirección owner2");
        owner1.setCity("Ciudad owner2");
        owner1.setTelephone("1231231231231");

        Pet pet2 = new Pet();
        pet2.setPetType(savedCatType);
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.now());
        pet2.setName("Gati");
        owner1.getPets().add(pet2);
        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Sagaz");
        vet1.getSpecialties().add(savedRadoilogy);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bilbo");
        vet2.setLastName("Bolson");
        vet1.getSpecialties().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets");
    }

}
