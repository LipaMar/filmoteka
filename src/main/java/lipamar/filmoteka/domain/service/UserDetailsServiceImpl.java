package lipamar.filmoteka.domain.service;

import lipamar.filmoteka.data.entities.User;
import lipamar.filmoteka.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = userRepository.findByUsername(s).stream().findFirst().orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("USER NOT FOUND");
        }

        log.info("findByUsername() : {}", user.getUsername());

        return org.springframework.security.core.userdetails.User.builder().passwordEncoder(encoder::encode)
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER").build();
    }

}
