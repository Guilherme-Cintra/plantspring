package com.guilhermecastro.amafloraSpringApi.controllers;

import com.guilhermecastro.amafloraSpringApi.entities.user.AuthenticationDTO;
import com.guilhermecastro.amafloraSpringApi.entities.user.ResgisterDTO;
import com.guilhermecastro.amafloraSpringApi.entities.user.User;
import com.guilhermecastro.amafloraSpringApi.entities.user.UserRole;
import com.guilhermecastro.amafloraSpringApi.repositories.UserRepository;
import com.guilhermecastro.amafloraSpringApi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/users")
public class UserController {

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid AuthenticationDTO data) {
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(emailPassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid ResgisterDTO data){
        if (this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserRole role = UserRole.USER;
        User user = new User(data.name(), data.familyName(), data.profilePicture(), data.email(), data.password(),  role);
        this.userRepository.save(user);

        return ResponseEntity.ok().body(user);
    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<User> getById(@PathVariable Long id) {
//        User user = userService.findById(id);
//        return ResponseEntity.ok().body(user);
//    }
//
//    @GetMapping(value = "/myplants/{id}")
//    public ResponseEntity<List<Plant>> getMyPlants(@PathVariable Long id) {
//        User user = userService.findById(id);
//        List plants = user.getPlants();
//        return ResponseEntity.ok().body(plants);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> findAll() {
//        List<User> users = userService.findAll();
//        return ResponseEntity.ok().body(users);
//    }
//
//    @PostMapping
//    public ResponseEntity<User> insert(@RequestBody User obj) {
//        obj = userService.insert(obj);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//        return ResponseEntity.created(uri).body(obj);
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
//        obj = userService.update(id, obj);
//        return ResponseEntity.ok().body(obj);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
//        Map<String, String> response = userService.delete(id);
//        return ResponseEntity.ok().body(response);
//    }

}
