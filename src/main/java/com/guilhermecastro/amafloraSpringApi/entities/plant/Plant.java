package com.guilhermecastro.amafloraSpringApi.entities.plant;


import com.guilhermecastro.amafloraSpringApi.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_plant")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
    private Boolean isActive;


    @ManyToOne
    @JoinColumn(name = "owner.id")
    private User owner;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public User getOwner() {
        return owner;
    }


}
