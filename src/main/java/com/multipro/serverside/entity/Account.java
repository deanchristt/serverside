package com.multipro.serverside.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String username;

    private String password;
}
