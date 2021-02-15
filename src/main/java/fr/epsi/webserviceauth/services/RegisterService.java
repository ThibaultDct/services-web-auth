package fr.epsi.webserviceauth.services;

import fr.epsi.webserviceauth.resources.dto.UserDTO;
import fr.epsi.webserviceauth.resources.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RegisterService {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDTO dto) {
        String sqlQuery = "INSERT INTO user_auth VALUES (:uuid, :user, :pw)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uuid", UUID.randomUUID());
        parameters.put("user", dto.getUsername());
        parameters.put("pw", passwordEncoder.encode(dto.getPassword()));

        jdbcTemplate.update(sqlQuery, parameters);
    }

}
