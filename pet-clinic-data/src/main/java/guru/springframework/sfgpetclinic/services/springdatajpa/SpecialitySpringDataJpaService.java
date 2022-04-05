package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("spring-data-jpa")
public class SpecialitySpringDataJpaService implements SpecialityService {

    private SpecialityRepository specialityRepository;

    public SpecialitySpringDataJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Speciality findById(Long id) {
        return this.specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        this.specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality save(Speciality object) {
        return this.specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        this.specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.specialityRepository.deleteById(id);
    }

}
