package com.example.starbuxcoffeeassignment.authentication;

import com.example.starbuxcoffeeassignment.entity.User;
import com.example.starbuxcoffeeassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User " + userName + " is not found"));
        String password = passwordEncoder.encode(user.getPassword());

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getUserRole() == true){
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }


        return new org.springframework.security.core.userdetails.User(userName,password, authorities);
    }
}
