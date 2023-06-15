package com.multipro.serverside.controller;

import com.multipro.serverside.dto.AccountDto;
import com.multipro.serverside.dto.RegisterDto;
import com.multipro.serverside.entity.Account;
import com.multipro.serverside.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AccountDto createAccount(@RequestBody RegisterDto registerDto){
        return authenticationService.createAccount(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountDto> login(@RequestBody Account account){
        return ResponseEntity.ok()
                .body(authenticationService.validateLogin(account));
    }
}
