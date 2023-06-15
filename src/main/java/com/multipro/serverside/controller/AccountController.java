package com.multipro.serverside.controller;

import com.multipro.serverside.dto.AccountDto;
import com.multipro.serverside.entity.Account;
import com.multipro.serverside.service.AccountService;
import com.multipro.serverside.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    private final AuthenticationService authenticationService;

    @GetMapping
    public List<Account> getAllAccount(@RequestHeader("token") String token){
        authenticationService.validateToken(token);
        return accountService.getAllAccount();
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@RequestHeader("token") String token, @PathVariable Long id){
        authenticationService.validateToken(token);
        return accountService.getAccountById(id);
    }
}
