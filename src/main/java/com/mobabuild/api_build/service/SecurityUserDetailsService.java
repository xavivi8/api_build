package com.mobabuild.api_build.service;

import com.mobabuild.api_build.repository.UserRepository;
import com.mobabuild.api_build.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var optUser = this.userRepository.findByEmail(email);

        if(optUser.isPresent()){
            return new SecurityUser(optUser.get());
        }

        throw new UsernameNotFoundException("User not found: " + email);
    }
}
