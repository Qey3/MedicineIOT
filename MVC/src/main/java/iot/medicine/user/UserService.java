package iot.medicine.user;

import iot.medicine.device.DevicesRepository;
import my.entity.mvc.user.RoleName;
import my.entity.mvc.user.Roles;
import my.entity.mvc.user.Users;

import my.entity.mvc.device.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserService {

    private static Logger log = Logger.getLogger("UserServiceLogger");

    @Autowired
    UserRepository userRepository;

    @Autowired
    DevicesRepository devicesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Transactional("transactionManager")
    public List<String> getAllRolesName() {
        return roleRepository.findRoleNames();
    }

    @Transactional("transactionManager")
    public boolean saveNewUser(Users user, RoleName roleName) {
        if (user == null || user.getName().isEmpty() || user.getEmail().isEmpty() ||
                user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        Integer userCountByLogin = userRepository.findUserCountByLogin(user.getLogin());
        if (userCountByLogin != null && userCountByLogin >= 1) return false;
        log.info("userCountByLogin = " + userCountByLogin);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Roles roles = roleRepository.findRoleByName(roleName);
        if(roles == null){
            roles = new Roles();
            roles.setRoleName(roleName);
        }
        user.setRoles(Set.of(roles));
        userRepository.save(user);

        return true;
    }

    @Transactional("transactionManager")
    public List<Device> getUserDeviceByName(String userName) {
        return userRepository.findUserDeviceByLogin(userName);
    }

    @Transactional("transactionManager")
    public Users findUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }

    @Transactional("transactionManager")
    public List<Device> getAllDevices() {
        return devicesRepository.getAllDevices();
    }
}
