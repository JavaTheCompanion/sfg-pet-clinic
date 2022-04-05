package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("spring-data-jpa")
public class PetSpringDataJpaService implements PetService {

    private PetRepository petRepository;

    public PetSpringDataJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet findById(Long id) {
        return this.petRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        this.petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet save(Pet object) {
        return this.petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        this.petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.petRepository.deleteById(id);
    }

}
