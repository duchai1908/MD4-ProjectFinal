package com.ra.md4projectapi.security.principle;

import com.ra.md4projectapi.model.entity.User;
import com.ra.md4projectapi.model.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailCustomService implements UserDetailsService {
    private final IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return UserDetailCustom.builder()
                .users(user)
                .authorities(user.getRoles().stream().map(roles-> new SimpleGrantedAuthority(roles.getRoleName().name())).toList())
                .build();
    }
}
