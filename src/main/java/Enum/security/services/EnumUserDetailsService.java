package Enum.security.services;

import Enum.data.models.User;
import Enum.security.models.SecureUser;
import Enum.services.EnumUserServices;
import Enum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EnumUserDetailsService implements UserDetailsService {
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(userEmail);
        UserDetails userDetails = new SecureUser(user);
        return userDetails;
    }
}
