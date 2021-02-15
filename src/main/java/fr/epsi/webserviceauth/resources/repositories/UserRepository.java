package fr.epsi.webserviceauth.resources.repositories;

import fr.epsi.webserviceauth.resources.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDTO getUserDetails(String username){

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String sqlQuery = "SELECT * FROM user_auth WHERE USERNAME = ?";
        List<UserDTO> list = jdbcTemplate.query(sqlQuery, new String[]{username}, (ResultSet rs, int rowNum) -> {
            UserDTO userDto = new UserDTO();
            userDto.setUsername(username);
            userDto.setPassword(rs.getString("mdp"));
            return userDto;
        });

        if (list != null && list.size() > 0){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
            grantedAuthorities.add(grantedAuthority);
            list.get(0).setGrantedAuthorities(grantedAuthorities);
            return list.get(0);
        }

        return null;
    }

}
