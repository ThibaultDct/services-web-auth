package fr.epsi.webserviceauth.resources.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class UserDTO {

    private String username;
    private String password;
    private Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
