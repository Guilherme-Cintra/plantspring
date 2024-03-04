package com.guilhermecastro.amafloraSpringApi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_plant")
public class Plant implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String scientificName;
    private String description;
    private String imageUrl;
    private LocalTime reminder_hour;
    private Integer reminder_frequency;
    private LocalDate last_water;

    @ManyToOne
    @JoinColumn(name = "owner.id")
    private User owner;

    public Plant() {
    }

    public Plant(Long id, String name, String scientificName, String description, String imageUrl, LocalTime reminder_hour, Integer reminder_frequency, LocalDate last_water, User owner) {

        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.reminder_hour = reminder_hour;
        this.reminder_frequency = reminder_frequency;
        this.last_water = last_water;
        this.owner = owner;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalTime getReminder_hour() {
        return reminder_hour;
    }

    public void setReminder_hour(LocalTime reminder_hour) {
        this.reminder_hour = reminder_hour;
    }

    public Integer getReminder_frequency() {
        return reminder_frequency;
    }

    public void setReminder_frequency(Integer reminder_frequency) {
        this.reminder_frequency = reminder_frequency;
    }

    public LocalDate getLast_water() {
        return last_water;
    }

    public void setLast_water(LocalDate last_water) {
        this.last_water = last_water;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
