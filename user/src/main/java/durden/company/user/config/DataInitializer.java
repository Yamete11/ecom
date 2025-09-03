package durden.company.user.config;

import durden.company.user.entities.Role;
import durden.company.user.entities.User;
import durden.company.user.repositories.RoleRepository;
import durden.company.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role();
        roleAdmin.setTitle("Admin");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setTitle("User");
        roleRepository.save(roleUser);

        roleRepository.flush();

        User user1 = new User();
        user1.setUsername("qwe");
        user1.setEmail("qwe@example.com");
        user1.setPassword(passwordEncoder.encode("qwe"));
        user1.setRole(roleAdmin);
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("asd");
        user2.setEmail("asd@example.com");
        user2.setPassword(passwordEncoder.encode("asd"));
        user2.setRole(roleUser);
        userRepository.save(user2);
    }

}

