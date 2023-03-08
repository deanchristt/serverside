package com.multipro.serverside.controller;

import com.multipro.serverside.dto.AccountDto;
import com.multipro.serverside.entity.Account;
import com.multipro.serverside.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serverside")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/accounts")
    public List<Account> getAllAccount(){
        return authenticationService.getAllAccount();
    }

    @GetMapping("/accounts/{id}")
    public AccountDto getAccountById(@PathVariable Long id){
        return authenticationService.getAccountById(id);
    }

    @PostMapping("/accounts")
    public AccountDto createAccount(@RequestBody Account account){
        return authenticationService.createAccount(account);
    }
}
