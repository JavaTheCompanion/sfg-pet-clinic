package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    /*public DataLoader() {
        this.ownerService = new OwnerServiceMapImpl();
        this.vetService = new VetServiceMapImpl();
    }*/


    @Override
    public void run(String... args) throws Exception {
        final Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Senthilkumar");
        owner1.setLastName("Sellamuthu");

        this.ownerService.save(owner1);

        final Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Saipranav");
        owner2.setLastName("Senthilkumar");

        this.ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        final Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Ravi");
        vet1.setLastName("Ashwin");

        this.vetService.save(vet1);

        final Vet vet2 = new Vet();
        vet2.setId(1L);
        vet2.setFirstName("Virat");
        vet2.setLastName("Kohli");

        this.vetService.save(vet2);

        System.out.println("Loaded Vets....");

    }
}
