package com.guilhermecastro.amafloraSpringApi.services;

import com.guilhermecastro.amafloraSpringApi.entities.User;
import com.guilhermecastro.amafloraSpringApi.repositories.UserRepository;
import com.guilhermecastro.amafloraSpringApi.services.exceptions.DatabaseException;
import com.guilhermecastro.amafloraSpringApi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
       Optional<User> user = userRepository.findById(id);
        return user.get();
    }
    public List<User> findAll(){
       return userRepository.findAll();
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public User update(Long id, User obj){
        try{
            User entity = userRepository.getReferenceById(id);
            updateData(entity, obj);
            return userRepository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }

    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setFamilyName(obj.getFamilyName());
        entity.setUsername(obj.getUsername());
        entity.setEmail(obj.getEmail());
        entity.setProfilePicture(obj.getProfilePicture());
        entity.setPassword(obj.getPassword());

    }



    public Map<String, String> delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            userRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User " + id.toString() + " deleted successfully.");
            return response;
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
