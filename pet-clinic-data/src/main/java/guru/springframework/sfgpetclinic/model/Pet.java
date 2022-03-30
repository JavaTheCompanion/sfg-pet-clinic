package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Pet extends BaseEntity {

    private String name;
    private LocalDate birthDate;
    private PetType petType;
    private Owner owner;

}
