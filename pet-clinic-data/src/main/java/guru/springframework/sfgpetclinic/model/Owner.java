package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "owner")
    @OrderBy("name ASC")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone,
                 Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if (pets != null && pets.size() > 0) {
            this.pets = pets;
        }
    }

    public Pet getPet(String name) {
        return this.getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        for (final Pet pet : this.pets) {
            if (!ignoreNew || !pet.isNew()) {
                if (pet.getName().equalsIgnoreCase(name)) {
                    return pet;
                }
            }
        }

        return null;
    }

}
