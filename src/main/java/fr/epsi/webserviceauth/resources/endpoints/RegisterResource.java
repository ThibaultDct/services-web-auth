package fr.epsi.webserviceauth.resources.endpoints;

import fr.epsi.webserviceauth.resources.dto.UserDTO;
import fr.epsi.webserviceauth.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterResource {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/users/register")
    public void register(@RequestBody UserDTO dto){
        registerService.registerUser(dto);
    }

}
