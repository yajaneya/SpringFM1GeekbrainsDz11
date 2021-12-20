package ru.yajaneya.SpringFM1GeekbrainsDz11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yajaneya.SpringFM1GeekbrainsDz11.entities.Authority;
import ru.yajaneya.SpringFM1GeekbrainsDz11.entities.User;
import ru.yajaneya.SpringFM1GeekbrainsDz11.repositories.UserRepositiry;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepositiry userRepositiry;

    public Optional<User> findByUsername(String username) {
        return userRepositiry.findByUsername(username);
    }

    public List<User> findAllUsers () {
        Iterable<User> usersIterable = userRepositiry.findAll();
        List<User> users = new ArrayList<>();
        usersIterable.forEach(u -> users.add(u));
        return users;

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), mapAuthoritiesToAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> mapAuthoritiesToAuthorities(Collection<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }

}
