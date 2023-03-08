package com.multipro.serverside.repository;

import com.multipro.serverside.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findFirstByUsername(String username);
}
