package com.guilhermecastro.amafloraSpringApi.resources;

import com.guilhermecastro.amafloraSpringApi.entities.Plant;
import com.guilhermecastro.amafloraSpringApi.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/plants")
public class PlantResource {

    @Autowired
    private PlantService plantService;

    @GetMapping
    public ResponseEntity<List<Plant>> findAll() {
        List<Plant> plants = plantService.findAll();
        return ResponseEntity.ok().body(plants);
    }

    @PostMapping
    public ResponseEntity<Plant> insert (@RequestBody Plant obj){
        obj = plantService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Plant> update(@PathVariable Long id, @RequestBody Plant obj){
        obj = plantService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable long id){
        Map<String, String> response = plantService.delete(id);
        return ResponseEntity.ok().body(response);
    }
}
