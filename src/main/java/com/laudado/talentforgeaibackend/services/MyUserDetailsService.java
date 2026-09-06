package com.laudado.talentforgeaibackend.services;

import com.laudado.talentforgeaibackend.models.User;
import com.laudado.talentforgeaibackend.repositories.UserRepository;
import com.laudado.talentforgeaibackend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;
    @Override
    public @NullMarked UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("User does not exist");
        return new UserPrincipal(user);
    }
}
