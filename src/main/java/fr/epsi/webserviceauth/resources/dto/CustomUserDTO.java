package fr.epsi.webserviceauth.resources.dto;

import org.springframework.security.core.userdetails.User;

public class CustomUserDTO extends User {

    private static final long serialVersionUID = 1L;

    public CustomUserDTO(UserDTO dto){
        super(dto.getUsername(), dto.getPassword(), dto.getGrantedAuthorities());
    }

}
