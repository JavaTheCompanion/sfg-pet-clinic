package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        final int count = this.petTypeService.findAll().size();

        if (count == 0) {
            this.loadData();
        }
    }

    private void loadData() {
        final PetType dog = new PetType();
        dog.setName("Dog");
        final PetType savedDogPetType = this.petTypeService.save(dog);

        final PetType cat = new PetType();
        cat.setName("Cat");
        final PetType savedCatPetType = this.petTypeService.save(cat);

        final Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = this.specialityService.save(radiology);

        final Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = this.specialityService.save(surgery);

        final Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        Speciality savedDentistry = this.specialityService.save(dentistry);

        final Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");

        final Pet mikesPet = new Pet();
        mikesPet.setName("Rosco");
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);

        owner1.getPets().add(mikesPet);

        this.ownerService.save(owner1);

        final Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        final Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setOwner(owner2);

        owner2.getPets().add(fionasCat);

        this.ownerService.save(owner2);

        final Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        this.visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        final Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        this.vetService.save(vet1);

        final Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
