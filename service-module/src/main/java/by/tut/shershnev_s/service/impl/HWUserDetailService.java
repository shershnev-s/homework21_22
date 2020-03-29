package by.tut.shershnev_s.service.impl;

import by.tut.shershnev_s.repository.model.User;
import by.tut.shershnev_s.service.UserService;
import by.tut.shershnev_s.service.model.HWUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HWUserDetailService implements UserDetailsService {

    private final UserService userService;

    public HWUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUserByUsername(username);
        user.getPassword();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new HWUser(user);
    }
}
