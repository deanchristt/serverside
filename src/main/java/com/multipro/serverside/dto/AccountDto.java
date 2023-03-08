package com.multipro.serverside.dto;

import lombok.Data;

@Data
public class AccountDto {

    private String username;
    private String password;
    private String accessToken;
}
