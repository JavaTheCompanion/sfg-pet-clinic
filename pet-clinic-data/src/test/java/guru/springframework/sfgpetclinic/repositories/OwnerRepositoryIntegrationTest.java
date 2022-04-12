package guru.springframework.sfgpetclinic.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OwnerRepositoryIntegrationTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
    }
}