package com.guilhermecastro.amafloraSpringApi.services;

import com.guilhermecastro.amafloraSpringApi.entities.plant.Plant;
import com.guilhermecastro.amafloraSpringApi.repositories.PlantRepository;
import com.guilhermecastro.amafloraSpringApi.services.exceptions.DatabaseException;
import com.guilhermecastro.amafloraSpringApi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;


    public List<Plant> findAll() {return plantRepository.findAll();}
    public Plant insert(Plant obj){
        return plantRepository.save(obj);
    }

    public Plant update(Long id, Plant obj){
        try {
            Plant entity = plantRepository.getReferenceById(id);
            updateData(entity, obj);
            return plantRepository.save(entity);
        } catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
            throw new ResourceNotFoundException(id);
        }
    }

    public Map<String, String> delete(Long id){
        if (!plantRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }

        try {
            plantRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Plant " + id.toString()  + " deleted successfully");
            return response;
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Plant entity, Plant obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setImageUrl(obj.getImageUrl());
        entity.setReminder_frequency(obj.getReminder_frequency());
        entity.setReminder_hour(obj.getReminder_hour());
        entity.setLast_water(obj.getLast_water());
    }
}
