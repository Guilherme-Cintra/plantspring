package com.guilhermecastro.amafloraSpringApi.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guilhermecastro.amafloraSpringApi.entities.plant.Plant;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class User implements Serializable, UserDetails {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String familyName;
    private String profilePicture;
    private String email;
    private String password;
    private UserRole role;



    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Plant> plants = new ArrayList<>();



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    public List<Plant> getPlants() {
        return plants;
    }

    public User(String name, String familyName, String profilePicture, String email, String password, UserRole role) {
        this.name = name;
        this.familyName = familyName;
        this.profilePicture = profilePicture;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
