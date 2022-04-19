package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    Page<Owner> findByLastNameLike(String lastName, Pageable pageable);

}
