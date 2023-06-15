package com.multipro.serverside.service;

import com.multipro.serverside.dto.AccountDto;
import com.multipro.serverside.entity.Account;
import com.multipro.serverside.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final MapperFacade mapperFacade;
    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }

    public AccountDto getAccountById(Long id){
        Account account = accountRepository.findById(id).orElseThrow();
        return mapperFacade.map(account, AccountDto.class);
    }
}
