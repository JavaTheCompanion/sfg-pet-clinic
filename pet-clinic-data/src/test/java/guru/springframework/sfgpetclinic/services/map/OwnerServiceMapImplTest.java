package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapImplTest {

    Long ownerId = 1L;
    String lastName = "Smith";
    private OwnerService ownerService;

    @BeforeEach
    void setUp() {
        this.ownerService = new OwnerServiceMapImpl(new PetTypeServiceMapImpl(), new PetServiceMapImpl());

        this.ownerService.save(Owner.builder().id(ownerId).lastName("Smith").build());
    }

    @Test
    void findById() {
        final Owner owner = this.ownerService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findAll() {
        final Set<Owner> owners = this.ownerService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        this.ownerService.deleteById(ownerId);
        assertEquals(0, this.ownerService.findAll().size());
    }

    @Test
    void delete() {
        this.ownerService.delete(this.ownerService.findById(ownerId));
        assertEquals(0, this.ownerService.findAll().size());
    }

    @Test
    void saveExistingId() {
        long id = 2L;
        final Owner owner2 = Owner.builder().id(id).lastName("Frank").build();

        final Owner savedOwner = this.ownerService.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveWithNoId() {
        final Owner savedOwner = this.ownerService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findByLastName() {
        Owner smith = this.ownerService.findByLastName(lastName);
        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner foo = this.ownerService.findByLastName("Foo");
        assertNull(foo);
    }
}