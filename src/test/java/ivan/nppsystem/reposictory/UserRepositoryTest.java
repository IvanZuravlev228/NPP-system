package ivan.nppsystem.reposictory;

import ivan.nppsystem.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserOk() {
        User user = new User();
        user.setFirstname("Ivan");
        user.setLastname("Zhuravlev");
        user.setEmail("test@gmail.com");
        user.setPassword("123123");
        user.setOrders(null);
        user.setRole(User.Role.USER);

        entityManager.persist(user);
        entityManager.flush();

        List<User> all = userRepository.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    public void updateUserOk() {
        User user = new User();
        user.setFirstname("Ivan");
        user.setLastname("Zhuravlev");
        user.setEmail("test@gmail.com");
        user.setPassword("123123");
        user.setOrders(null);
        user.setRole(User.Role.USER);

        userRepository.save(user);

        user.setFirstname("UpdatedName");
        userRepository.save(user);

        User updatedUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getFirstname()).isEqualTo("UpdatedName");
    }

    @Test
    public void deleteUserOk() {
        User user = new User();
        user.setFirstname("Ivan");
        user.setLastname("Zhuravlev");
        user.setEmail("test@gmail.com");
        user.setPassword("123123");
        user.setOrders(null);
        user.setRole(User.Role.USER);

        userRepository.save(user);

        userRepository.delete(user);

        assertThat(userRepository.findById(user.getId())).isEmpty();
    }

    @Test
    public void findUserByIdOk() {
        User user = new User();
        user.setFirstname("Ivan");
        user.setLastname("Zhuravlev");
        user.setEmail("test@gmail.com");
        user.setPassword("123123");
        user.setOrders(null);
        user.setRole(User.Role.USER);

        userRepository.save(user);

        User foundUser = userRepository.findById(user.getId()).orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(user);
    }
}