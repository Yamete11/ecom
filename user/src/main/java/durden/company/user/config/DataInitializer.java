package durden.company.user.config;

import durden.company.user.entities.Role;
import durden.company.user.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        Role roleAdmin = new Role();
        roleAdmin.setTitle("Admin");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setTitle("User");
        roleRepository.save(roleUser);
    }


}
