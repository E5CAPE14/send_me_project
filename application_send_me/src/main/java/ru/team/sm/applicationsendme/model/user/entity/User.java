package ru.team.sm.applicationsendme.model.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_entity")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Email
    @Column(nullable = false,length = 100,unique = true)
    @NonNull
    private String email;
    @NonNull
    @Column(nullable = false,length = 100)
    @Length(min = 10)
    private String password;
    @Column(nullable = false,length = 35,unique = true,updatable = false)
    @NonNull
    @Length(min = 4)
    private String login;
    @Column(name = "time_of_registry",updatable = false,nullable = false)
    @CreatedDate
    @NonNull
    private LocalDateTime timeOfRegistry;

    @Column(name = "is_enabled")
    private Boolean isEnable = true;
    @Column(name = "link_image")
    private String linkImage;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Role.class,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    @NonNull
    private Role role;

    public User(@NonNull String email, @NonNull String password, @NonNull String login, @NonNull LocalDateTime timeOfRegistry, @NonNull Role role) {
        this.email = email;
        this.password = password;
        this.login = login;
        this.timeOfRegistry = timeOfRegistry;
        this.role = role;
        this.linkImage = "user_default.jpg";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

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
        return isEnable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", timeOfRegistry=" + timeOfRegistry +
                ", isEnable=" + isEnable +
                ", linkImage='" + linkImage + '\'' +
                ", role=" + role +
                '}';
    }
}
