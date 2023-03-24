package com.example.batuhan.project.auth;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.batuhan.project.entity.PersonRole;
import com.example.batuhan.project.request_response.LoginRequest;
import com.example.batuhan.project.request_response.RegisterRequest;
import com.example.batuhan.project.service.PersonService;

@RestController
@RequestMapping("/user")
public class AuthConroller {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    PersonService personService;
    
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            return ResponseEntity.ok(tokenManager.generateToken(loginRequest.getEmail()));
        } catch (Exception e) {
            throw e;
        }
    }
    
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
    	return ResponseEntity.ok(personService.createPerson(registerRequest));
    }
    
    @GetMapping(value = "/getUserRoles")
    public Set<PersonRole> getUserRoles(@RequestParam String token) {
    	return personService.getPerson(tokenManager.getEmailToken(token)).get().getRoles();
    }
    //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImJhdHVoYW4iLCJyb2xlcyI6IltVU0VSXSIsImlhdCI6MTY3OTU3ODQ1NywiZXhwIjoxNjc5NTc4NzU3fQ.2VpiWc2JE74_HY-MzK_jdD7fIR60OvWHWTFcxf-7LIk
    @GetMapping(value = "/tokenControl")
    public Boolean tokenControl(@RequestParam String token) {
    	return tokenManager.tokenControl(token);
    }
    @GetMapping(value = "/getEmail")
    public String getEmailToken(@RequestParam String token) {
    	return tokenManager.getEmailToken(token);
    }

}
