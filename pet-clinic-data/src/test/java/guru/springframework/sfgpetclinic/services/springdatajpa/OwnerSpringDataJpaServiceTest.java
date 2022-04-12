package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    Owner returnOwner;
    @InjectMocks
    private OwnerSpringDataJpaService ownerService;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private PetTypeRepository petTypeRepository;

    @BeforeEach
    public void beforeAll() {
        //MockitoAnnotations.openMocks(this);
        //this.ownerService = new OwnerSpringDataJpaService(this.ownerRepository, this.petRepository, this.petTypeRepository);
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findById() {
        when(this.ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = this.ownerService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(this.ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = this.ownerService.findById(1L);
        assertNull(owner);
    }

    @Test
    void findAll() {
        final Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());

        when(this.ownerRepository.findAll()).thenReturn(returnOwnersSet);

        final Set<Owner> owners = this.ownerService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());

        verify(this.ownerRepository, times(1)).findAll();
    }

    @Test
    void save() {
        final Owner ownerToSave = Owner.builder().id(1L).build();
        when(this.ownerRepository.save(any())).thenReturn(returnOwner);
        final Owner savedOwner = this.ownerService.save(ownerToSave);
        assertNotNull(savedOwner);
        verify(this.ownerRepository).save(any());
    }

    @Test
    void delete() {
        this.ownerService.delete(returnOwner);
        verify(this.ownerRepository).delete(any());
        verify(this.ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        this.ownerService.deleteById(1L);
        verify(this.ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {

        when(this.ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner smith = this.ownerService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(this.ownerRepository).findByLastName(any());
    }
}