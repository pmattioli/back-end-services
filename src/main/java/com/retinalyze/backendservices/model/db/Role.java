package com.retinalyze.backendservices.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "ret_user_types")
public class Role implements GrantedAuthority {

    public static final String USER_ADMIN = "Admin";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "typeid")
    private Long id;
    @Column(name = "typename")
    private String authority;

}
