package com.guilhermecastro.amafloraSpringApi.resources;

import com.guilhermecastro.amafloraSpringApi.entities.Plant;
import com.guilhermecastro.amafloraSpringApi.entities.User;
import com.guilhermecastro.amafloraSpringApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/myplants/{id}")
    public ResponseEntity<List<Plant>> getMyPlants(@PathVariable Long id) {
        User user = userService.findById(id);
        List plants = user.getPlants();
        return ResponseEntity.ok().body(plants);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = userService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        Map<String, String> response = userService.delete(id);
        return ResponseEntity.ok().body(response);
    }

}
