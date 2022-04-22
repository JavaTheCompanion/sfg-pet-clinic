package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Vet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VetService extends CrudService<Vet, Long> {

    Page<Vet> findAll(Pageable pageable);

}
