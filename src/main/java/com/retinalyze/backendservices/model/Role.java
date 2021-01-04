package com.retinalyze.backendservices.model;

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
    public static final String AUTHOR_ADMIN = "AUTHOR_ADMIN";
    public static final String BOOK_ADMIN = "BOOK_ADMIN";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "typeid")
    private Long id;
    @Column(name = "typename")
    private String authority;

}
