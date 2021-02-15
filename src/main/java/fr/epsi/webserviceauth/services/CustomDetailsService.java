package fr.epsi.webserviceauth.services;

import fr.epsi.webserviceauth.resources.dto.CustomUserDTO;
import fr.epsi.webserviceauth.resources.dto.UserDTO;
import fr.epsi.webserviceauth.resources.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDto = userRepository.getUserDetails(username);
        CustomUserDTO customUser = new CustomUserDTO(userDto);
        return customUser;
    }

}
