package maciej.kubis.blog;

import lombok.RequiredArgsConstructor;
import maciej.kubis.blog.user.domain.Privilege;
import maciej.kubis.blog.user.domain.Role;
import maciej.kubis.blog.user.domain.RoleEnum;
import maciej.kubis.blog.user.domain.User;
import maciej.kubis.blog.user.repository.PrivilegeRepository;
import maciej.kubis.blog.user.repository.RoleRepository;
import maciej.kubis.blog.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InitClass {

    private final String firstName = "Maciej";
    private final String secondName = "Kubis";
    private final String email = "admin";
    private final String description = "Example";
    private final String password = "admin";

    private final String firstName2 = "Maciej";
    private final String secondName2 = "Kubis";
    private final String email2 = "user";
    private final String description2 = "Example";
    private final String password2 = "user";


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;


    public void initData() {
        final Privilege readPrivilege = newPrivilege("ROLE_READ_PRIVILEGE");
        final Privilege modifyPrivelege = newPrivilege("ROLE_MODIFY_PRIVILAGE");
        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege,
                modifyPrivelege));
        final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(readPrivilege));


        final Role roleUser = newRole(RoleEnum.ROLE_USER.getValue(), userPrivileges);
        addUser(roleUser);
        final Role roleAdmin = newRole(RoleEnum.ROLE_ADMIN.getValue(), adminPrivileges);
        addAdmin(roleAdmin);
    }

    private void addAdmin(final Role roleUser) {
        User user = new User();
        user.setDescription(description);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.getRoles().add(roleUser);
        userRepository.save(user);

    }

    private void addUser(final Role roleUser) {
        User user = new User();
        user.setDescription(description2);
        user.setFirstName(firstName2);
        user.setSecondName(secondName2);
        user.setEmail(email2);
        user.setPassword(passwordEncoder.encode(password2));
        user.setEnabled(true);
        user.getRoles().add(roleUser);
        userRepository.save(user);
    }



    private Privilege newPrivilege(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private Role newRole(final String name, final List<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);

        return role;
    }
}
