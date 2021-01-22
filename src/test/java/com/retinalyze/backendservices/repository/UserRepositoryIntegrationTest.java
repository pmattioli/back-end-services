package com.retinalyze.backendservices.repository;

import com.retinalyze.backendservices.model.db.Role;
import com.retinalyze.backendservices.model.db.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnListContainingUser() {
        // given
        Role adminRole = new Role(null, Role.USER_ADMIN);
        entityManager.persist(adminRole);
        User alex = new User(null, "alex", "pass", false, adminRole);
        entityManager.persist(alex);
        entityManager.flush();

        // when
        List<User> found = userRepository.findByUsernameContaining(alex.getUsername());

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getUsername()).isEqualTo(alex.getUsername());
    }

}
