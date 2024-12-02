package com.api.rms.configs;

import com.api.rms.entities.UserEntity;
import com.api.rms.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = getUserFromDatabase(username);
        CustomUserDetails userDetails = null;

        if (userEntity != null) {
            userDetails = new CustomUserDetails(userEntity);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }

    private UserEntity getUserFromDatabase(String username) {
        Optional<UserEntity> userEntityOpt = userRepo.findByUsername(username);

        return userEntityOpt.orElse(null);
    }
}
