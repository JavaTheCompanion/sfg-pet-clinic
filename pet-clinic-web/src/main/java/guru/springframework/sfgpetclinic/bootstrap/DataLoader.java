package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

//@Component
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
        final PetType dog = PetType.builder()
                .name("Dog").build();
        final PetType savedDogPetType = this.petTypeService.save(dog);

        final PetType cat = PetType.builder()
                .name("Cat").build();
        final PetType savedCatPetType = this.petTypeService.save(cat);

        final Speciality radiology = Speciality.builder()
                .description("Radiology").build();
        Speciality savedRadiology = this.specialityService.save(radiology);

        final Speciality surgery = Speciality.builder()
                .description("Surgery").build();
        Speciality savedSurgery = this.specialityService.save(surgery);

        final Speciality dentistry = Speciality.builder()
                .description("dentistry").build();
        Speciality savedDentistry = this.specialityService.save(dentistry);

        final Owner owner1 = Owner.builder()
                .firstName("Michael")
                .lastName("Weston")
                .address("123 Brickerel")
                .city("Miami")
                .telephone("1231231234").build();

        final Pet mikesPet = Pet.builder()
                .name("Rosco")
                .birthDate(LocalDate.now())
                .petType(savedDogPetType)
                .owner(owner1).build();

        owner1.getPets().add(mikesPet);

        this.ownerService.save(owner1);

        final Owner owner2 = Owner.builder()
                .firstName("Fiona")
                .lastName("Glenanne")
                .address("123 Brickerel")
                .city("Miami")
                .telephone("1231231234").build();

        final Pet fionasCat = Pet.builder()
                .name("Just Cat")
                .birthDate(LocalDate.now())
                .petType(savedCatPetType)
                .owner(owner2).build();

        owner2.getPets().add(fionasCat);

        this.ownerService.save(owner2);

        final Visit catVisit = Visit.builder()
                .pet(fionasCat)
                .visitDate(LocalDate.now())
                .description("Sneezy Kitty").build();

        this.visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        final Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);

        this.vetService.save(vet1);

        final Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
