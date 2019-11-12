package iot.medicine.security;

import my.entity.mvc.user.Users;
import iot.medicine.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service("authService")
public class AuthenticationService implements UserDetailsService {

    private static Logger log = Logger.getLogger("AuthenticationService");

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional("transactionManager")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByName() username = " + username);
        Users user = userRepository.findUserByLogin(username);

        if(user == null){
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        return new User(user.getLogin(), user.getPassword(), user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority("ROLE_" + roles.getRoleName().name()))
                .collect(Collectors.toList()));
    }
}
