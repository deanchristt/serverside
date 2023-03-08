package com.multipro.serverside.service;

import com.multipro.serverside.dto.AccountDto;
import com.multipro.serverside.entity.Account;
import com.multipro.serverside.repository.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper mapperFacade;

    @Autowired
    private JwtService jwtService;

    private final String USERNAME = "username";


    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }

    public AccountDto getAccountById(Long id){
        Account account = accountRepository.findById(id).orElseThrow();
        AccountDto accountDto=  mapperFacade.map(account, AccountDto.class);
        return accountDto;
    }

    public AccountDto createAccount(Account account){
        accountRepository.save(account);
        AccountDto accountDto=  mapperFacade.map(account, AccountDto.class);
        accountDto.setAccessToken(jwtService.generateToken(accountDto.getUsername()));
        return accountDto;
    }

    public AccountDto validateLogin(Account account){
        Account accounts = accountRepository.findFirstByUsername(account.getUsername());
        if (accounts == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }else{
            AccountDto accountDto = mapperFacade.map(account, AccountDto.class);
            accountDto.setAccessToken(jwtService.generateToken(account.getUsername()));
            return accountDto;
        }

    }

    public void validateToken(String token){
        try {
            if (jwtService.isTokenExpired(token)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            } else {
                Claims claims = jwtService.getAllClaimsFromToken(token);
                Account account = accountRepository.findFirstByUsername(claims.get(USERNAME, String.class));
                if (account == null) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }
            }
        }catch (JwtException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "FORMAT TOKEN SALAH");
        }
    }

}
