package com.funaki.filaProducao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_User;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;
}
